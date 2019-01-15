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
import org.etriks.security.log.AccessLogService
import org.etriks.security.registration.RegistrationValidatorService
import org.springframework.validation.FieldError

import javax.mail.MessagingException

class BioasterMemberAccountController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "POST"]

    static scope="session";
    static final projectName="bioaster";

    def boolean authSucc=false;

    def AccessLogService accessLogService
    def UserRegistrationService userRegistrationService;

    // Create the instance of registration validate service
    def RegistrationValidatorService registrationValidatorService= RegistrationValidatorService.getInstance();
    def OrganizationManagerService organizationManagerService;
    def GroupManagerService groupManagerService;


    def index(Integer max) {
        def bioasterValidators=registrationValidatorService.getBioasterValidatorList();
        render(view:"memberAuth",model :[validatorList:bioasterValidators])
    }

    /*
   * This action force user to enter a member password to enter the member registration
   * */
    def memberAuth(){

        String userRawPwd=params.password;
        def PasswordEncrytor pwdEnc=new PasswordEncrytor();
        //Beware the algo name majuscule or minuscule can cause not match in the password test
        String userPwd=pwdEnc.digestBase64("md5",userRawPwd);


        def String pwd="{md5}5zqkx3nsSCBmFq7LQoI1qQ==";


        if(userPwd.equals(pwd)){

            //if the password enter by the user is correct, assign authSucc to true;
            authSucc=true;

            render(view: "privacyAgreement");
        }
        else {

            def ArrayList<String> msgs= new ArrayList<String>();
            msgs.add("The password which you enter is wrong. If you do not know the password, please contact the Bioaster CZAR");
            flash.messages=msgs;
            def bioasterValidators=registrationValidatorService.getBioasterValidatorList();
            render(view:"memberAuth",model :[validatorList:bioasterValidators])

        }
    }

    def bioasterAccountCreate(){
        //This test is to prevent the user enter the create url to bypass the password block
        if(authSucc==true){
            def agreeToMail=params.get("AgreeToMail")
            //get the client ip from his request
            def clientIp=request.getRemoteAddr();
            log.info(LogMessages.getAgreePrivacyMessage(clientIp)) ;
            //database log
            accessLogService.saveLog(LogMessages.getAgreePrivacyMessage(clientIp),"visitor", ProjectId.bioaster,LogType.Info);
            def bioasterMemberAccountInstance=new BioasterMemberAccount(params)

            //get the bioaster project validator name list
            def bioasterValidatorNameList=registrationValidatorService.getBioasterValidatorNameList();

            //get the hosting validator name list
            def hostingValidatorNameList=registrationValidatorService.getHostingValidatorNameList();

            //get list of all group names
            List<String> allGroupNames = groupManagerService.getAllPosixGroupNames(projectName);
            //get list of all organization names.
            List<String> allOrganizationNames=organizationManagerService.getAllOrganizationName();

            render(view: "create" , model:[bioasterMemberAccountInstance:bioasterMemberAccountInstance,bioasterValidatorList:bioasterValidatorNameList,hostingValidatorList:hostingValidatorNameList,organizationList: allOrganizationNames,groupList:allGroupNames])
            // respond new BioasterMemberAccount(params)
        }
        else {
            redirect (action:"index")
        }
    }

    @Transactional
    def save(BioasterMemberAccount bioasterMemberAccountInstance) {

        if (authSucc==true){
            if(userRegistrationService.userEmailExisted(bioasterMemberAccountInstance.email)){
                flash.message="Your emails has been used to register as etriks member, You may already have an account. Reset your password, if you forgot your password!!!"
                redirect(controller: "bioasterEndUserSpaceManager",action: "forgotPassword")
            }
                else{
                    //registration request has no error, start the registration process
                    if (bioasterMemberAccountInstance.validate()){
                        //fail to store the registration request,return to the creation page
                        if (!bioasterMemberAccountInstance.save(flush: true)) {
                            flash.message="Unable to save your request,please try again!!!"
                            //fail to save the member in DB. redirect to view create
                            //get the bioaster project validator name list
                            def bioasterValidatorNameList=registrationValidatorService.getBioasterValidatorNameList();

                            //get the hosting validator name list
                            def hostingValidatorNameList=registrationValidatorService.getHostingValidatorNameList();
                            //get list of all group names
                            List<String> allGroupNames = groupManagerService.getAllPosixGroupNames(projectName);
                            //get list of all organization names.
                            List<String> allOrganizationNames=organizationManagerService.getAllOrganizationName();

                            render(view: "create", model: [bioasterMemberAccountInstance: bioasterMemberAccountInstance,bioasterValidatorList:bioasterValidatorNameList,hostingValidatorList:hostingValidatorNameList,organizationList: allOrganizationNames,groupList:allGroupNames])

                            return
                        }
                        //store the registration request for validation
                        else {
                            //log the user action to the access log
                            def clientIp=request.getRemoteAddr();
                            log.info(LogMessages.getRequestSubmissionMessage(clientIp,bioasterMemberAccountInstance.firstName,bioasterMemberAccountInstance.lastName));
                            accessLogService.saveLog(LogMessages.getRequestSubmissionMessage(clientIp,bioasterMemberAccountInstance.firstName,bioasterMemberAccountInstance.lastName),"visitor", ProjectId.bioaster,LogType.Info);
                            //succed to save the member in DB. informe the validateur,and render the success view


                            if (!this.sendEmail(params.hostingValidator,params.bioasterValidator)){flash.message="Unable to send emails to the validators, But your registration request is well recorded"}
                            //prevent user to go back to the create form page
                            redirect(action:"registrationSuccess",params: [firstName: bioasterMemberAccountInstance.getFirstName(),lastName:bioasterMemberAccountInstance.getLastName(),email:bioasterMemberAccountInstance.getEmail(),groups:bioasterMemberAccountInstance.getGroups(),organization:bioasterMemberAccountInstance.getOrganization()])
                        }

                    }

                    else {

                        //get the client ip from his request
                        def clientIp=request.getRemoteAddr();
                        log.info(LogMessages.getRequestSubmissionFailMessage(clientIp,bioasterMemberAccountInstance.firstName,bioasterMemberAccountInstance.lastName));
                        accessLogService.saveLog(LogMessages.getRequestSubmissionFailMessage(clientIp,bioasterMemberAccountInstance.firstName,bioasterMemberAccountInstance.lastName),"visitor",ProjectId.bioaster,LogType.Info)
                        def ArrayList<String> msgs= new ArrayList<String>();

                        def FieldError firstNameError=bioasterMemberAccountInstance.errors.getFieldError("firstName");
                        if (firstNameError!=null){

                            msgs.add("Your first name is too long, please enter another name.");
                        }

                        def FieldError lastNameError=bioasterMemberAccountInstance.errors.getFieldError("lastName");
                        if (lastNameError!=null){
                            msgs.add("Your last name is too long, please enter another name.");
                        }

                        def FieldError emailError=bioasterMemberAccountInstance.errors.getFieldError("email");
                        if (emailError!=null){
                            msgs.add("Your email adress is not correct, please enter another email adress.");
                        }

                        def FieldError pwdError=bioasterMemberAccountInstance.errors.getFieldError("password");
                        if (pwdError!=null){
                            msgs.add("Your password is too short, please enter another password. The password must contain at least 8 letters or numbers");
                        }

                        def FieldError confirmPwdError=bioasterMemberAccountInstance.errors.getFieldError("confirmPassword");
                        if (confirmPwdError!=null){
                            msgs.add("The confirm password does not match the password, please check again.");
                        }
                        def FieldError organizationError=bioasterMemberAccountInstance.errors.getFieldError("organization");
                        if (organizationError!=null){
                            msgs.add("organization is wrong.");
                        }
//show the error message
                        flash.messages=msgs;
                        //reinitial validator list
                        //get the bioaster project validator name list
                        def bioasterValidatorNameList=registrationValidatorService.getBioasterValidatorNameList();

                        //get the hosting validator name list
                        def hostingValidatorNameList=registrationValidatorService.getHostingValidatorNameList();

                        //get list of all group names
                        List<String> allGroupNames = groupManagerService.getAllPosixGroupNames(projectName);
                        //get list of all organization names.
                        List<String> allOrganizationNames=organizationManagerService.getAllOrganizationName();

                        render ( view: 'create' ,model:[bioasterMemberAccountInstance: bioasterMemberAccountInstance,bioasterValidatorList:bioasterValidatorNameList,hostingValidatorList:hostingValidatorNameList,organizationList: allOrganizationNames,groupList:allGroupNames])

                    }
                }
            }


        else {
            def bioasterValidators=registrationValidatorService.getBioasterValidatorList();
            render(view:"memberAuth",model :[validatorList:bioasterValidators])
        }



    }

    def registrationSuccess(String firstName,String lastName,String email,String groups,String organization){
/*log out his session so user can not go back*/
        authSucc=false;

/**/
        render(view : "registrationSuccess", model : [firstName:firstName,lastName: lastName,email:email,groups:groups,organization: organization]);

    }

    def edit(BioasterMemberAccount bioasterMemberAccountInstance) {
        respond bioasterMemberAccountInstance
    }


    private boolean sendEmail(String hostingValidator,String projectValidator){

        def boolean result=true;

        CCJAutoMail emailer = new CCJAutoMail();
        //the domains of these email addresses should be valid, or it will fail

        String msg=new MailMessage().getRegistrationRequestAlertMessage(params.firstName+" "+params.lastName);


        //Send email to the hosting validator's mail

        def String hostingValidatorEmail= registrationValidatorService.getHostingValidatorEmail(hostingValidator);
        def String projectValidatorEmail= registrationValidatorService.getBioasterValidatorEmail(projectValidator);

        if (hostingValidatorEmail==null||projectValidatorEmail==null) {
            flash.message="Can't find the mail of your validator"}

        try{

        //Send emails to bioaster validator
        emailer.sendMail(projectValidatorEmail," New user registration request for Bioaster etriks platform",msg);
            //Send emails to hosting validators
            emailer.sendMail(hostingValidatorEmail," New user registration request for Bioaster etriks platform", msg);

        }catch (MessagingException e){
            result=false;
            throw new MessagingException(e);
        }
return result;

    }




}
