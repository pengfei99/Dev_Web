package org.etriks.security.register
import grails.transaction.Transactional
import org.etriks.log.LogMessages
import org.etriks.log.LogType
import org.etriks.log.ProjectId
import org.etriks.mail.CCJAutoMail
import org.etriks.mail.MailMessage
import org.etriks.mail.ValidatorMail
import org.etriks.security.Ldap.UserRegistrationService
import org.etriks.security.encryption.PasswordEncrytor
import org.etriks.security.encryption.PasswordStrength
import org.etriks.security.log.AccessLogService
import org.springframework.validation.FieldError

class EtriksMemberAccountController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    static scope="session";

    static final projectName="etriks"

    def boolean authSucc=false;

    def AccessLogService accessLogService;
    def UserRegistrationService userRegistrationService;
    /*def OrganizationManagerService organizationManagerService;
    */


    def index() {
        render(view:"memberAuth")
    }


    /*
    * This action force user to enter a member password to enter the member registration
    * */
    def memberAuth(){

        String userRawPwd=params.password;
        //println(userRawPwd)
        def PasswordEncrytor pwdEnc=new PasswordEncrytor();
        String userPwd=pwdEnc.digestBase64("md5",userRawPwd);

        def String pwd="{md5}3Wm6CBEPHIJygkwSCdR88g==";


        if(userPwd.equals(pwd)){

            //if the password enter by the user is correct, assign authSucc to true;
            authSucc=true;

            render(view: "privacyAgreement");
        }
        else {
            //println userPwd
            def ArrayList<String> msgs= new ArrayList<String>();
            msgs.add("The password which you enter is wrong. If you do not know the password, please contact your work package leader");
            flash.messages=msgs;
            render (view:"memberAuth")
        }
    }

    def eTriksAccountCreate() {
        //This test is to prevent the user enter the create url to bypass the password block
        if(authSucc==true){
            def agreeToMail=params.get("AgreeToMail")
            //get the client ip from his request
             def clientIp=request.getRemoteAddr();
            log.info(LogMessages.getAgreePrivacyMessage(clientIp)) ;
            accessLogService.saveLog(LogMessages.getAgreePrivacyMessage(clientIp),"visitor",ProjectId.etriks,LogType.Info)
            render(view : "create", model:[etriksMemberAccountInstance:  new EtriksMemberAccount(params)])
        }
        else {
            render (view:"memberAuth")
        }
    }

    def show(EtriksMemberAccount etriksMemberAccountInstance) {
       respond etriksMemberAccountInstance

    }

    def create() {
        respond new EtriksMemberAccount(params)
    }
    @Transactional
    def save(EtriksMemberAccount etriksMemberAccountInstance) {
        if(authSucc==true){

            //account emails already existed,render view reset password
            //In new ldap archi,also need to check if users in the etriks_member or not, if in means double account, if not, accept account creation
            if(userRegistrationService.userEmailExisted(etriksMemberAccountInstance.email)){
                flash.message="Your emails has been used to register as etriks member, You may already have an account. Reset your password, if you forgot your password!!!"
            redirect(controller: "etriksEndUserAccountManager",action: "forgotPassword")
            }

                //account emails is not used
            else {

                if (etriksMemberAccountInstance.validate()){
                    if (!etriksMemberAccountInstance.save(flush: true)) {
                        render(view: "create", model: [etriksMemberAccountInstance: etriksMemberAccountInstance])
                        return
                    }

                    def clientIp=request.getRemoteAddr();
                    log.info(LogMessages.getRequestSubmissionMessage(clientIp,etriksMemberAccountInstance.firstName,etriksMemberAccountInstance.lastName));
                    accessLogService.saveLog(LogMessages.getRequestSubmissionMessage(clientIp,etriksMemberAccountInstance.firstName,etriksMemberAccountInstance.lastName),"visitor",ProjectId.etriks,LogType.Info)
                    /*  flash.message = message(code: 'default.created.message', args: [message(code: 'etriksMemberAccount.label', default: 'EtriksMemberAccount'), etriksMemberAccountInstance.id])*/

                    //succed to save the member in DB. informe the validateur,and render the success view
                    CCJAutoMail emailer = new CCJAutoMail();
                    //the domains of these email addresses should be valid, or it will fail

                    String msg=new MailMessage().getRegistrationRequestAlertMessage(params.firstName+" "+params.lastName);


                    //email is the validator's mail
                    ValidatorMail vm=new ValidatorMail();
                    /* System.out.println(vm.getMail(params.validator));*/

                    emailer.sendMail(vm.getMail(params.validator)," New user registration request ", msg);


                    redirect(action : "registrationSuccess", params : [firstName: etriksMemberAccountInstance.getFirstName(),lastName: etriksMemberAccountInstance.getLastName(),email:etriksMemberAccountInstance.getEmail(),workPackage: etriksMemberAccountInstance.getWorkPackage(),organization: etriksMemberAccountInstance.getOrganization()]);

                }

                else {
                    //get the client ip from his request
                    def clientIp=request.getRemoteAddr();
                    //file log
                    log.info(LogMessages.getRequestSubmissionFailMessage(clientIp,etriksMemberAccountInstance.firstName,etriksMemberAccountInstance.lastName));
                    //DataBase log
                    accessLogService.saveLog(LogMessages.getRequestSubmissionFailMessage(clientIp,etriksMemberAccountInstance.firstName,etriksMemberAccountInstance.lastName),"visitor",ProjectId.etriks,LogType.Info)
                    def ArrayList<String> msgs= new ArrayList<String>();

                    def FieldError firstNameError=etriksMemberAccountInstance.errors.getFieldError("firstName");
                    if (firstNameError!=null){

                        msgs.add("Your first name is too long, please enter another name.");
                    }

                    def FieldError lastNameError=etriksMemberAccountInstance.errors.getFieldError("lastName");
                    if (lastNameError!=null){
                        msgs.add("Your last name is too long, please enter another name.");
                    }

                    def FieldError emailError=etriksMemberAccountInstance.errors.getFieldError("email");
                    if (emailError!=null){
                        msgs.add("Your email adress is not correct, please enter another email adress.");
                    }

                    def FieldError pwdError=etriksMemberAccountInstance.errors.getFieldError("password");
                    if (pwdError!=null){
                        msgs.add("Your password is too short, please enter another password. The password must contain at least 8 letters or numbers");
                    }

                    def FieldError confirmPwdError=etriksMemberAccountInstance.errors.getFieldError("confirmPassword");
                    if (confirmPwdError!=null){
                        msgs.add("The confirm password does not match the password, please check again.");
                    }

                    flash.messages=msgs;
                    render(view : "create", model:[etriksMemberAccountInstance:  etriksMemberAccountInstance])
                }
            }

            }

        else render (view:"memberAuth")
    }


    def registrationSuccess(String firstName,String lastName,String email,String workPackage,String organization){
             /*log out his session so user can not go back*/
        authSucc=false;
        render(view : "registrationSuccess", model : [firstName:firstName,lastName: lastName,email:email,workPackage:workPackage,organization: organization]);

    }

def checkPasswordStrength(){
    def String pwd=params.password;
    PasswordStrength pwdStr=new PasswordStrength();
    def String pwdLevel=pwdStr.checkPasswordStrength(pwd);
    render "Your password strength is ${pwdLevel}"
}

}
