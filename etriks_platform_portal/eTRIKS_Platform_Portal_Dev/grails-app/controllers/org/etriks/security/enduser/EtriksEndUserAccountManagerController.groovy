package org.etriks.security.enduser

import grails.transaction.Transactional
import org.etriks.log.LogMessages
import org.etriks.log.LogType
import org.etriks.log.ProjectId
import org.etriks.mail.CCJAutoMail
import org.etriks.mail.MailMessage
import org.etriks.security.encryption.PasswordStrength
import org.etriks.security.ldapnew.authentication.UserDetails
import org.etriks.security.ldapnew.entry.exception.ChangePasswordStatus
import org.etriks.security.ldapnew.entry.UserAccountsManager
import org.etriks.security.ldapnew.serverprofile.LdapConnectorConfig
import org.etriks.security.ldapnew.serverprofile.PubLdapConnectorConfig
import org.etriks.security.log.AccessLogService
import org.etriks.security.password.RandomPasswordGenerator

import javax.mail.MessagingException

@Transactional
class EtriksEndUserAccountManagerController {


    def AccessLogService accessLogService;
    //Define the etriks ldap profil instance
    def LdapConnectorConfig prodLdap=new PubLdapConnectorConfig();

    def index() {

    }

    def changePassword(){
        def UserDetails userInstance=session.userDetails;
        if(userInstance!=null){
            def String uid=userInstance.getUserName();
            render(view:"changePassword", model:[uid :uid] )
        }
        else redirect(controller:"AccessControlException",action:"notLoggedIn")
    }


    def forgotPassword(){
        render(view:"resetPassword")
    }


    //This method allow user who forget user password to reset a random password. Then they can use this to change their own password.
    //To reset the password, the user needs to enter uid and email. if the uid or email is not registered ,the reset request will be refused.
    def resetLdapUserAccountPassword(){

        RandomPasswordGenerator pg=new RandomPasswordGenerator();


        UserAccountsManager uam=new UserAccountsManager(prodLdap);
        String uid=params.uid;
        String email=params.mail;
        String userDn=uam.getUserDn(uid);
        def clientIp=request.getRemoteAddr();

        accessLogService.saveLog(clientIp+" wants to reset password of "+uid,clientIp,ProjectId.etriks,LogType.Info)
        if(userDn==null){
            flash.error="The uid which you entered does not exist!!!"
            accessLogService.saveLog(clientIp+" reset password of "+uid+" failed, uid doesnot exist",clientIp,ProjectId.etriks,LogType.Info)
            // uid wrong, redirect reset password page
            render(view:"resetPassword")
        }
        else {
            String mail=uam.getUserMailByDn(userDn);
            //System.out.println(mail);


            //If the entered email address match the registered email address, start the reset password procedure
            if(mail.equals(email)){
                String pwd1=pg.generateRandomPassword();
                boolean result=uam.resetUserPassword(userDn,pwd1);

                //Reset password succeful redirect to user space page
                if(result){
                    // System.out.println("Your password has been reset, you will receive your new password by email. You need to change it as soon as possible "+pwd1);
                    flash.message="Your password has been reset, you will receive your new password by email. You need to change it as soon as possible "
                    //Send new password to user mail box
                    CCJAutoMail emailer = new CCJAutoMail();
                    String msg=new MailMessage().getResetPasswordMessage("etriks", uid,pwd1);
                    try {
                        emailer.sendMail(mail, "Password reset notification", msg);
                    } catch (MessagingException e) {
                        log.error("unable to send user the reset password notification email in controller: etriksEndUserSpaceManager, action :resetLdapUserAccountPassword. Java exception "+e.toString());
                    }
                    accessLogService.saveLog(clientIp+" reset the password of "+uid+" successfully",clientIp,ProjectId.etriks,LogType.Info)
                    redirect(action:"index")
                }
                //reset password fail, report to error log
                else {
                    flash.error= "Unable to reset your password. Please contact your project leader for more information";
                    log.error("Reset user password failed in controller: etriksEndUserSpaceManager, action :resetLdapUserAccountPassword ");
                    render(view:"resetPassword")
                }

            }
            //The entered email address dosenot match the registered email address, stop the reset. alert maybe an attack
            else {
                flash.error="The entered email does not correspond your registered email!!! If you trying to hack the system, be ware, your IP address has been registered."
                accessLogService.saveLog(clientIp+" reset password of "+uid+" failed, email doesnot match",clientIp,ProjectId.etriks,LogType.Info)
                render(view:"resetPassword")
                // System.out.println("The email which you entered does not correspond your registered email!!! If you trying to hack in the system, be ware, your IP address has been registered.");
            }
        }
    }


    def changeLdapUserAccountPassword(String uid){
        def UserDetails userInstance=session.userDetails;
        String oldPassword=params.oldPassword;
        String newPassword=params.newPassword;
        String confNewPassword=params.confNewPassword;
        PasswordStrength pwdStr=new PasswordStrength();
        def int pwdStrengthLevel=pwdStr.checkPasswordStrengthInInt(newPassword);
        if (pwdStrengthLevel<=3){
            accessLogService.saveLog("Change password failed, password is too weak",userInstance.username,ProjectId.etriks,LogType.Info)
            flash.message="Your password is too weak!!! Please choose a strong one"
            redirect(action:"changePassword")
        }
else{

        //Test if the confirm password equals the new password
        if(newPassword.equals(confNewPassword)){

            //Creat an instance of the end user account manager, it take the searchbase and the parentdirectory of the user account as argument

UserAccountsManager userAccountManager=new UserAccountsManager(prodLdap);
            def result=userAccountManager.changeUserPassword(uid, oldPassword, newPassword);

           // println (result.toString());
            //Test the result of the change password, if success, redirect to the index with a success message
            if(result.equals(ChangePasswordStatus.Success)){
                flash.message="Your password has been modified,try to relogin to test it"
                log.info(LogMessages.getChangePwdMessage(uid))
                accessLogService.saveLog(LogMessages.getChangePwdMessage(uid),userInstance.username,ProjectId.etriks,LogType.Info)
                redirect(action:"index")
            }
            if(result.equals(ChangePasswordStatus.UidNotFind)){
                accessLogService.saveLog("Change password failed, account name does not exist",userInstance.username,ProjectId.etriks,LogType.Info)
                flash.message="Your account name does not exist, contact your admin for more information"
                redirect(action:"changePassword")
            }
            if(result.equals(ChangePasswordStatus.WrongPassword)){
                accessLogService.saveLog("Change password failed, old password is wrong",userInstance.username,ProjectId.etriks,LogType.Info)
                flash.message="Your password is wrong,contact your admin for more information"
                redirect(action:"changePassword")
            }
            if(result.equals(ChangePasswordStatus.Fail)){
                accessLogService.saveLog("Change password failed, Ldap server is not responding",userInstance.username,ProjectId.etriks,LogType.Error)
                flash.message="The server is not responding, your password cannot be changed. Please try later"
                redirect(action:"index")
            }
        }
        else {
            accessLogService.saveLog("Change password failed, confirm new password and new password are different",userInstance.username,ProjectId.etriks,LogType.Info)
            flash.message="Your confirm new password and new password are different!!!"
            redirect(action:"changePassword")
        }
        }
    }

    def checkPasswordStrength(){
        def String pwd=params.password;
        PasswordStrength pwdStr=new PasswordStrength();

        def String pwdLevel=pwdStr.checkPasswordStrength(pwd);
        render "Your password strength is ${pwdLevel}"
    }
}
