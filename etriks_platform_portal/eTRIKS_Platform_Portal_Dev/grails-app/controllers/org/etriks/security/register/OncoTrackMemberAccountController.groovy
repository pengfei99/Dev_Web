package org.etriks.security.register
import grails.transaction.Transactional
import org.etriks.log.LogMessages
import org.etriks.log.LogType
import org.etriks.log.ProjectId
import org.etriks.mail.CCJAutoMail
import org.etriks.mail.MailMessage
import org.etriks.security.Ldap.GroupManagerService
import org.etriks.security.Ldap.OrganizationManagerService
import org.etriks.security.Ldap.UserRegistrationService
import org.etriks.security.encryption.PasswordEncrytor
import org.etriks.security.encryption.PasswordStrength
import org.etriks.security.log.AccessLogService
import org.etriks.security.registration.RegistrationValidatorService
import org.springframework.validation.FieldError

import javax.mail.MessagingException

class OncoTrackMemberAccountController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    static scope="session";

    static final projectName="oncotrack"

    def boolean authSucc=false;

    def AccessLogService accessLogService
    def UserRegistrationService userRegistrationService;

    // Create the instance of registration validate service
    def RegistrationValidatorService registrationValidatorService= RegistrationValidatorService.getInstance();

    def OrganizationManagerService organizationManagerService;
    def GroupManagerService groupManagerService;


    def index() {
        def oncotrackValidators=registrationValidatorService.getOncotrackValidatorList();
        render(view:"memberAuth",model:[validatorList:oncotrackValidators])
        /* redirect(action: "list", params: params)*/

    }


    /*
    * This action force user to enter a member password to enter the member registration
    * */
    def memberAuth(){

        String userRawPwd=params.password;
        def PasswordEncrytor pwdEnc=new PasswordEncrytor();
        //Beware the algo name majuscule or minuscule can cause not match in the password test
        String userPwd=pwdEnc.digestBase64("md5",userRawPwd);


        def String pwd="{md5}h2EMhUMfCF8bu0BEQwQDbw==";


        if(userPwd.equals(pwd)){

            //if the password enter by the user is correct, assign authSucc to true;
            authSucc=true;

            render(view: "privacyAgreement");
        }
        else {

            def ArrayList<String> msgs= new ArrayList<String>();
            msgs.add("The password which you enter is wrong. If you do not know the password, please contact the Oncotrack CZAR");
            flash.messages=msgs;
            def oncotrackValidators=registrationValidatorService.getOncotrackValidatorList();
            render(view:"memberAuth",model:[validatorList:oncotrackValidators])
        }
    }

    def oncoTrackAccountCreate() {
        //This test is to prevent the user enter the create url to bypass the password block
        if(authSucc==true){
            def agreeToMail=params.get("AgreeToMail")
            //get the client ip from his request
            def clientIp=request.getRemoteAddr();
            log.info(LogMessages.getAgreePrivacyMessage(clientIp)) ;
            //database log
accessLogService.saveLog(LogMessages.getAgreePrivacyMessage(clientIp),"visitor", ProjectId.oncoTrack,LogType.Info);

            //get the oncotrack project validator name list
            def oncotrackValidatorNameList=registrationValidatorService.getOncotrackValidatorNameList();

            //get the hosting validator name list
            def hostingValidatorNameList=registrationValidatorService.getHostingValidatorNameList();


            //get list of all group names
            List<String> allGroupNames = groupManagerService.getAllPosixGroupNames(projectName);
            //get list of all organization names.
            List<String> allOrganizationNames=organizationManagerService.getAllOrganizationName();

            render(view : "create", model:[oncoTrackMemberAccountInstance:   new OncoTrackMemberAccount(params),oncotrackValidatorList:oncotrackValidatorNameList,hostingValidatorList:hostingValidatorNameList,organizationList: allOrganizationNames,groupList:allGroupNames])
        }
        else {
            def oncotrackValidators=registrationValidatorService.getOncotrackValidatorList();
            render(view:"memberAuth",model:[validatorList:oncotrackValidators])
        }
    }
    @Transactional
    def save(OncoTrackMemberAccount oncoTrackMemberAccountInstance) {

        if (authSucc==true){

            if(userRegistrationService.userEmailExisted(oncoTrackMemberAccountInstance.email)){
                flash.message="Your emails has been used to register as etriks member, You may already have an account. Reset your password, if you forgot your password!!!"
                redirect(controller: "oncoTrackEndUserSpaceManager",action: "forgotPassword")
            }
            else{
               if (oncoTrackMemberAccountInstance.validate()){
                   if (!oncoTrackMemberAccountInstance.save(flush: true)) {
                       //fail to save the member in DB. redirect to view create

                       //get the oncotrack project validator name list
                       def oncotrackValidatorNameList=registrationValidatorService.getOncotrackValidatorNameList();

                       //get the hosting validator name list
                       def hostingValidatorNameList=registrationValidatorService.getHostingValidatorNameList();


                       //get list of all group names
                       List<String> allGroupNames = groupManagerService.getAllPosixGroupNames(projectName);
                       //get list of all organization names.
                       List<String> allOrganizationNames=organizationManagerService.getAllOrganizationName();
                       render(view: "create", model: [oncoTrackMemberAccountInstance: oncoTrackMemberAccountInstance,oncotrackValidatorList:oncotrackValidatorNameList,hostingValidatorList:hostingValidatorNameList,organizationList: allOrganizationNames,groupList:allGroupNames])
                       return
                   }
                   else {
                       //log the user action to the access log
                       def clientIp=request.getRemoteAddr();
                       log.info(LogMessages.getRequestSubmissionMessage(clientIp,oncoTrackMemberAccountInstance.firstName,oncoTrackMemberAccountInstance.lastName));
                       accessLogService.saveLog(LogMessages.getRequestSubmissionMessage(clientIp,oncoTrackMemberAccountInstance.firstName,oncoTrackMemberAccountInstance.lastName),"visitor", ProjectId.oncoTrack,LogType.Info);
                       //succed to save the member in DB. informe the validateur,and render the success view


                       //send mail to validator,
                       if (!this.sendEmail(params.hostingValidator,params.oncoTrackValidator)){flash.message="Unable to send emails to the validators, But your registration request is well recorded"}

                       redirect(action:"registrationSuccess",params: [firstName: oncoTrackMemberAccountInstance.getFirstName(),lastName:oncoTrackMemberAccountInstance.getLastName(),email:oncoTrackMemberAccountInstance.getEmail(),groups:oncoTrackMemberAccountInstance.getGroups(),organization:oncoTrackMemberAccountInstance.getOrganization()])
                   }




               }

               else {
                   //get the client ip from his request
                   def clientIp=request.getRemoteAddr();
                   log.info(LogMessages.getRequestSubmissionFailMessage(clientIp,oncoTrackMemberAccountInstance.firstName,oncoTrackMemberAccountInstance.lastName));
                   accessLogService.saveLog(LogMessages.getRequestSubmissionFailMessage(clientIp,oncoTrackMemberAccountInstance.firstName,oncoTrackMemberAccountInstance.lastName),"visitor",ProjectId.oncoTrack,LogType.Info)
                   def ArrayList<String> msgs= new ArrayList<String>();

                   def FieldError firstNameError=oncoTrackMemberAccountInstance.errors.getFieldError("firstName");
                   if (firstNameError!=null){

                       msgs.add("Your first name is too long, please enter another name.");
                   }

                   def FieldError lastNameError=oncoTrackMemberAccountInstance.errors.getFieldError("lastName");
                   if (lastNameError!=null){
                       msgs.add("Your last name is too long, please enter another name.");
                   }

                   def FieldError emailError=oncoTrackMemberAccountInstance.errors.getFieldError("email");
                   if (emailError!=null){
                       msgs.add("Your email adress is not correct, please enter another email adress.");
                   }

                   def FieldError pwdError=oncoTrackMemberAccountInstance.errors.getFieldError("password");
                   if (pwdError!=null){
                       msgs.add("Your password is too short, please enter another password. The password must contain at least 8 letters or numbers");
                   }

                   def FieldError confirmPwdError=oncoTrackMemberAccountInstance.errors.getFieldError("confirmPassword");
                   if (confirmPwdError!=null){
                       msgs.add("The confirm password does not match the password, please check again.");
                   }

                   flash.messages=msgs;
                   //get the oncotrack project validator name list
                   def oncotrackValidatorNameList=registrationValidatorService.getOncotrackValidatorNameList();

                   //get the hosting validator name list
                   def hostingValidatorNameList=registrationValidatorService.getHostingValidatorNameList();


                   //get list of all group names
                   List<String> allGroupNames = groupManagerService.getAllPosixGroupNames(projectName);
                   //get list of all organization names.
                   List<String> allOrganizationNames=organizationManagerService.getAllOrganizationName();
                   render(view : "create", model:[oncoTrackMemberAccountInstance: oncoTrackMemberAccountInstance,oncotrackValidatorList:oncotrackValidatorNameList,hostingValidatorList:hostingValidatorNameList,organizationList: allOrganizationNames,groupList:allGroupNames])
               }
           }
           }


        else {
            def oncotrackValidators=registrationValidatorService.getOncotrackValidatorList();
            render(view:"memberAuth",model:[validatorList:oncotrackValidators])
        }
    }

    def registrationSuccess(String firstName,String lastName,String email,String groups,String organization){
/*log out his session so user can not go back*/
        authSucc=false;

/**/
        render(view : "registrationSuccess", model : [firstName:firstName,lastName: lastName,email:email,groups:groups,organization: organization]);

    }

    def checkPasswordStrength(){
        def String pwd=params.password;
        PasswordStrength pwdStr=new PasswordStrength();
        def String pwdLevel=pwdStr.checkPasswordStrength(pwd);
        render "Your password strength is ${pwdLevel}"
    }

    private boolean sendEmail(String hostingValidator,String projectValidator){

        def boolean result=true;

        CCJAutoMail emailer = new CCJAutoMail();
        //the domains of these email addresses should be valid, or it will fail

        String msg=new MailMessage().getRegistrationRequestAlertMessage(params.firstName+" "+params.lastName);


        //Send email to the hosting validator's mail

        def String hostingValidatorEmail= registrationValidatorService.getHostingValidatorEmail(hostingValidator);
        def String projectValidatorEmail= registrationValidatorService.getOncotrackValidatorEmail(projectValidator);

        if (hostingValidatorEmail==null||projectValidatorEmail==null) {
            flash.message="Can't find the mail of your validator"}

        try{

            //Send emails to oncotrack validator
            emailer.sendMail(projectValidatorEmail," New user registration request for OncoTrack etriks platform",msg);
            //Send emails to hosting validators
            emailer.sendMail(hostingValidatorEmail," New user registration request for OncoTrack etriks platform", msg);

        }catch (MessagingException e){
            result=false;
            throw new MessagingException(e);
        }
        return result;

    }
}