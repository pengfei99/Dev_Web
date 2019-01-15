package org.bioaster.registration

import org.bioaster.mail.MailManager
import org.bioaster.mail.MailMessage
import org.bioaster.security.OrganizationManagerService
import org.bioaster.security.UserRegistrationService
import org.bioaster.security.config.ILdapServerConnectorConfig
import org.bioaster.security.config.OpenLdapConfig
import org.bioaster.security.config.BUMPConfig
import org.bioaster.security.encryption.PasswordChecker
import org.bioaster.security.encryption.PasswordEncoder
import org.bioaster.security.registration.CaptchaService
import org.bioaster.utils.StringToIntCheck

import javax.imageio.ImageIO
import javax.mail.MessagingException

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BioasterUserRegistrationRequestController {

    def OrganizationManagerService organizationManagerService;
    def UserRegistrationService userRegistrationService;
    /*def RegistrationValidatorService registrationValidatorService=RegistrationValidatorService.getInstance();*/
    ILdapServerConnectorConfig ldapConfig=OpenLdapConfig.getInstance();


    boolean notRobot=false;

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {

    }

    def capchaCheck(){
        /*Block to test the capcha response*/
        def captchaId=request.getSession().getId()
        String response=params.get("captchaResponse");
       //Test if the user captcha response input is numeric
        if(!StringToIntCheck.isNumeric(response)){
            flash.error="The answer must be a number, please enter again."
            redirect(action:"index")
        }
            //Test if the capcha answer is right
        else if(!CaptchaService.getInstance().validateResponseForID(captchaId,response)){
           flash.error="The answer which you entered is wrong, please enter again"
            redirect(action:"index")
        }
        else {
            notRobot=true;
            redirect(action:"privacyAgreement")
        }

    }
    def privacyAgreement(){
        if(notRobot)
        render(view:"privacyAgreement")
        else redirect(action: "index")
    }
    def bioasterRegistrationRequestCreate() {

          if(notRobot){
              BioasterUserRegistrationRequest bURRInstance= new BioasterUserRegistrationRequest(params)
              List<String> allOrganizationNames=organizationManagerService.getAllOrganizationName(ldapConfig);
              def validatorNameList=userRegistrationService.getValidatorNameList(ldapConfig);
              render(view: "create" , model:[bURRInstance:bURRInstance,validatorList:validatorNameList,organizationList: allOrganizationNames])
          }
        else redirect(action:"index")

    }

    @Transactional
    def save(BioasterUserRegistrationRequest bioasterUserRegistrationRequest) {
        if(!notRobot) {
            redirect(action: "index")
        }
        else {
        PasswordChecker pwdStr = PasswordChecker.getInstance();
        int pwdStrengthLevel = pwdStr.checkPasswordStrengthInInt(bioasterUserRegistrationRequest.password);
        if (bioasterUserRegistrationRequest == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

       else if (bioasterUserRegistrationRequest.hasErrors()) {
            transactionStatus.setRollbackOnly()
           // respond bioasterUserRegistrationRequest.errors, view:'create'
            // return
            List<String> confirmPwdErrors = bioasterUserRegistrationRequest.errors.getFieldErrors("confirmPassword")
            List<String> emailErrors = bioasterUserRegistrationRequest.errors.getFieldErrors("email")

           if(!confirmPwdErrors.isEmpty()) {
               String message = "Your confirm password is different of your password."
               this.retry(bioasterUserRegistrationRequest, message);
           }
           else if (!emailErrors.isEmpty()){
               String message = "The email address which you entered is not valid."
               this.retry(bioasterUserRegistrationRequest, message);
           }
            else {
               String message = "The size of the filed which you entered is too long."
               this.retry(bioasterUserRegistrationRequest, message);
           }

        }

        else if (userRegistrationService.userEmailExisted(ldapConfig,bioasterUserRegistrationRequest.email)) {
            String message = "Your emails has been already used for BIOASTER account, You may already have an account. Reset your password, if you forgot your password."
            this.retry(bioasterUserRegistrationRequest,message);
        }

        else if(pwdStrengthLevel<BUMPConfig.passwordStrength){
           String message = "Your password is too weak, your password must have letters, digits and special characters."
            this.retry(bioasterUserRegistrationRequest,message);
        }

        else{

            //create the instance of the password encrypter.
            PasswordEncoder pwdEncoder=new PasswordEncoder();
            String password=bioasterUserRegistrationRequest.password
            //Encrypte the password,
            String encPassword = pwdEncoder.encPassword(password);
            bioasterUserRegistrationRequest.password = encPassword
            bioasterUserRegistrationRequest.confirmPassword = encPassword


        if(!bioasterUserRegistrationRequest.save(flush:true)){
            //fail to save the member in DB. redirect to view create
            def message = "Unable to save your request, please try again."
            bioasterUserRegistrationRequest.password = password
            bioasterUserRegistrationRequest.confirmPassword = password
            this.retry(bioasterUserRegistrationRequest,message);
        }
            else {

            notRobot=false;
            MailManager emailer= MailManager.getInstance();
            MailMessage mailMessage=MailMessage.getInstance();
            String firstName=bioasterUserRegistrationRequest.firstName
            String lastName=bioasterUserRegistrationRequest.lastName
            String validatorName=bioasterUserRegistrationRequest.validator
            String validatorMail=userRegistrationService.getValidatorMail(ldapConfig,validatorName)
            String msg=mailMessage.getRegistrationRequestAlertMessage(lastName+" "+firstName);
            try {
            emailer.sendMail(validatorMail,"New User registration Request",msg)}
            catch (MessagingException e) {
               log.error("can't send mail to validator"+e.toString())
            }
            render(view:"success",model:[firstName:firstName,lastName:lastName,
                                         email:bioasterUserRegistrationRequest.email,organization:bioasterUserRegistrationRequest.organization,
                                         validator:validatorName])
        }
        }
        }
    }



    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'bioasterUserRegistrationRequest.label', default: 'BioasterUserRegistrationRequest'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    private void retry(BioasterUserRegistrationRequest bioasterUserRegistrationRequest,String message){
        flash.message = message
        //fail to save the member in DB. redirect to view create

        //get bioaster validator name list
        def validatorNameList = userRegistrationService.getValidatorNameList(ldapConfig);

        //get list of all organization names.
        List<String> allOrganizationNames = organizationManagerService.getAllOrganizationName(ldapConfig);

        render(view: "create", model: [bURRInstance: bioasterUserRegistrationRequest, validatorList: validatorNameList, organizationList: allOrganizationNames])
    }

    def createCaptcha(){
        def captchaId=request.getSession().getId();
        def capService=CaptchaService.getInstance();
        capService.creatCaptcha(captchaId);
        def bufferCaptchaImage=capService.getCaptchaImageForID(captchaId);
        byte[] captchaImage=null;
        ByteArrayOutputStream outputStream =new ByteArrayOutputStream();
        ImageIO.write(bufferCaptchaImage,"png",outputStream);
        outputStream.flush();
        captchaImage=outputStream.toByteArray();
        response.outputStream << captchaImage

    }

    /*This method render a password complexity check result
    * It's called by a javascript ajax */
    def checkPassword(){
        String password=params.password
        PasswordChecker pwdChecker= PasswordChecker.getInstance();
        String pwdStrength=pwdChecker.checkPasswordStrength(password);
        render(template: "passwordStrength",model:[pwdStrength:pwdStrength])
    }


}
