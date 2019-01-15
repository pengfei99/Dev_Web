package org.etriks.security.register

import org.etriks.log.LogMessages
import org.etriks.log.LogType
import org.etriks.log.ProjectId
import org.etriks.mail.CCJAutoMail
import org.etriks.mail.MailMessage
import org.etriks.security.encryption.PasswordEncrytor
import org.etriks.security.encryption.PasswordStrength
import org.etriks.security.ldap.entry.UserAccountManager
import org.etriks.security.ldap.serverprofile.PublicMemberLdapConnectorConfig
import org.etriks.security.log.AccessLogService
import org.etriks.security.registration.CaptchaService
import org.etriks.utils.StringToIntCheck
import org.springframework.validation.FieldError

import javax.imageio.ImageIO

class UserAccountController {

    def AccessLogService accessLogService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        //get the client ip from his request
      //  def clientIp=request.getRemoteAddr();
      //  log.info(clientIp+" connect to the sever") ;
        redirect(action: "privacyAgreement", params: params)
    }

    def privacyAgreement(){
        String licenseTerm =''' ''';
        render(view: "privacyAgreement",model : [licenseTerm: licenseTerm])
    }

    def eTriksAccountCreate() {
        def agreeToMail=params.get("AgreeToMail")
        //get the client ip from his request
        def clientIp=request.getRemoteAddr();
        accessLogService.saveLog(clientIp+ " agree to the privacy protection term",clientIp,ProjectId.etriks,LogType.Info)
        render(view : "eTriksAccountCreate", model:[userAccountInstance: new UserAccount(params)])
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
 /*   def show(UserAccount userAccountInstance) {
        respond userAccountInstance
    }

    def create() {
        respond new UserAccount(params)
    }*/


    def save() {

        /*Block to test the capcha response*/
        def captchaId=request.getSession().getId()
        def String response=params.get("captchaResponse");
//Test if the user captcha response input is numeric
if(!StringToIntCheck.isNumeric(response)){
    def ArrayList<String> msgs= new ArrayList<String>();
    msgs.add("The answer which you enter must be a number, please enter again code!!!")
    flash.messages=msgs
    render(view:"eTriksAccountCreate",model:[userAccountInstance:new UserAccount(params)])
}
        else{
        def userAccountInstance = new UserAccount(params);
        def captchaTest=false

           captchaTest=CaptchaService.getInstance().validateResponseForID(captchaId,response)

        /*End of the capcha test*/
        if(captchaTest==false){
            def ArrayList<String> msgs= new ArrayList<String>();
            msgs.add("The answer which you enter is wrong, please enter again code!!!")
            flash.messages=msgs
            render(view:"eTriksAccountCreate",model:[userAccountInstance:userAccountInstance])
        }
else{
        //Check the constraints success
        if (userAccountInstance.validate()){
            //get the client ip from his request
            def clientIp=request.getRemoteAddr();
            log.info(clientIp+ " try to creat account with first name: "+
                    userAccountInstance.firstName+" and last name: "+userAccountInstance.lastName+" passes the attribute validations");



            //Turn the first letter to the Maj, turn the rest to the minus, here I don't test if there are null or not
            //Because, we already restrain that they are not nullable or blank. So no need
            def firstName=userAccountInstance.firstName.substring(0, 1).toUpperCase() + userAccountInstance.firstName.substring(1).toLowerCase();
            def lastName=userAccountInstance.lastName.substring(0, 1).toUpperCase() + userAccountInstance.lastName.substring(1).toLowerCase();

            def accountid=userAccountInstance.id
            def cn=firstName+" "+lastName
            def uid=firstName.substring(0,1).toLowerCase()+"."+lastName.toLowerCase()
            def sn=lastName
            def givenName=firstName
            def password=userAccountInstance.password
            def gidNumber="5000"
            def uidNumber=10000;
            def homeDirectory="/home/null"
            def loginShell="/bin/false"
            def email=userAccountInstance.email


            /*
             * The following code add the ldap user account entry into the Ldap server
             */

            PublicMemberLdapConnectorConfig pubMemLdap=new PublicMemberLdapConnectorConfig();
            //creat the instance of the user account mananger
            UserAccountManager accReg = new UserAccountManager(pubMemLdap,"ou=Users,dc=etriks,dc=eu","ou=Users,dc=etriks,dc=eu");

            /******************************************Determine the best uidNumber for nes users*****************************/
            def int size=accReg.getUserSize();
            def  max=10001+size;
            while(uidNumber<max){
                if(accReg.uidNumberExisted(Integer.toString(uidNumber))){
                    uidNumber++;
                }
                else {
                    break;}
            }
            /*****************************************End of the determination********************************************/
            def uidNum=String.valueOf(uidNumber)

            //create the instance of the password encrypter.
            PasswordEncrytor pwdEncrypter=new PasswordEncrytor();

            //Encrypte the password,
            String encPassword=pwdEncrypter.digestBase64("sha", password);

            //println encPassword;

            Map<String,String> entryAttributes=new HashMap<String,String>();

            entryAttributes.put("cn", cn);
            entryAttributes.put("uid", uid);
            entryAttributes.put("sn", sn);
            entryAttributes.put("userPassword", encPassword);
            entryAttributes.put("uidNumber", uidNum);
            entryAttributes.put("gidNumber", gidNumber);
            entryAttributes.put("homeDirectory", homeDirectory);
            entryAttributes.put("loginShell", loginShell);
            entryAttributes.put("mail", email);
            entryAttributes.put("givenName", givenName);

            boolean flag = accReg.creatUserAccount(entryAttributes);

            if (flag==true){

                accessLogService.saveLog("User "+uid+" account has been created in the community ldap server",uid,ProjectId.etriks,LogType.Info)
                //close the ldap connection context
                accReg.closeContext();

                accessLogService.saveLog(LogMessages.getPublicMemberCreationMessage(clientIp,userAccountInstance.firstName,userAccountInstance.lastName),"visitor", ProjectId.etriks,LogType.Info);
                //Create an instance of mail
                CCJAutoMail emailer = new CCJAutoMail();

                //the domains of these email addresses should be valid, or it will fail

                String msg=new MailMessage().getAccountActivationMessage(uid);

                //try to send a confirmation mail to the user. if failed ask user to redo the registration
                //try {

                emailer.sendMail(email,"Account creation confirmation", msg);

                //} catch (MessagingException e) {

                //	def ArrayList<String> msgs= new ArrayList<String>();
                //  msgs.add("We cannot send a confirmation email to you, Please try another email adress.");
                //  flash.messages=msgs;
                //redirect(action:"eTriksAccountCreate")
                //		}

                redirect (action : 'registrationSuccess', params : [firstName:firstName,lastName:lastName,uid:uid,email:email]);
            }
            //fail to add the user account in the LDap server
            else {
                def ArrayList<String> msgs= new ArrayList<String>();
                msgs.add("We cannot create your account, Your name has been already used.");
                flash.messages=msgs;
              render(view:"eTriksAccountCreate" , model : [userAccountInstance:userAccountInstance])
            }
        }
        //Check the constraints failed
        else {
            //get the client ip from his request
            def clientIp=request.getRemoteAddr();
            log.info(clientIp+ " try to creat account with first name: "+
                    userAccountInstance.firstName+" and last name: "+userAccountInstance.lastName+" failed the attribute validations");
            def ArrayList<String> msgs= new ArrayList<String>();

            def FieldError firstNameError=userAccountInstance.errors.getFieldError("firstName");
            if (firstNameError!=null){

                msgs.add("Your first name is too long, please enter another name.");
            }

            def FieldError lastNameError=userAccountInstance.errors.getFieldError("lastName");
            if (lastNameError!=null){
                msgs.add("Your last name is too long, please enter another name.");
            }

            def FieldError emailError=userAccountInstance.errors.getFieldError("email");
            if (emailError!=null){
                msgs.add("Your email adress is not correct, please enter another email adress.");
            }

            def FieldError pwdError=userAccountInstance.errors.getFieldError("password");
            if (pwdError!=null){
                msgs.add("Your password is too short, please enter another password.");
            }

            def FieldError confirmPwdError=userAccountInstance.errors.getFieldError("confirmPassword");
            if (confirmPwdError!=null){
                msgs.add("The confirm password does not match the password, please check again.");
            }

            flash.messages=msgs;
            render(view:"eTriksAccountCreate",model:[userAccountInstance:userAccountInstance])
        }

        }
    }
    }



    def checkPasswordStrength(){
        def String pwd=params.password;
        PasswordStrength pwdStr=new PasswordStrength();
        def String pwdLevel=pwdStr.checkPasswordStrength(pwd);
        render "Your password strength is ${pwdLevel}"
    }
    def registrationSuccess(String firstName,String lastName,String uid,String email){

        render(view:'registrationSuccess',model:[firstName: firstName, lastName:lastName, uid:uid, email:email ]);
    }
}
