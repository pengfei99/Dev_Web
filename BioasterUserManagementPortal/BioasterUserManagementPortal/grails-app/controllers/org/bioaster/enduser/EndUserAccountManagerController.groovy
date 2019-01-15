package org.bioaster.enduser

import org.bioaster.log.AccessLogMessage
import org.bioaster.log.AccessLogService
import org.bioaster.log.LogType
import org.bioaster.mail.MailManager
import org.bioaster.mail.MailMessage
import org.bioaster.security.MemberManagerService
import org.bioaster.security.auth.UserDetails
import org.bioaster.security.config.ILdapServerConnectorConfig
import org.bioaster.security.config.OpenLdapConfig
import org.bioaster.security.config.BUMPConfig
import org.bioaster.security.encryption.PasswordChecker
import org.bioaster.security.encryption.RandomPwdGenerator

import javax.mail.MessagingException

class EndUserAccountManagerController {

    def AccessLogService accessLogService;
    def MemberManagerService memberManagerService;
    ILdapServerConnectorConfig ldapConf = OpenLdapConfig.getInstance();

    def index() {}

    def changePassword() {
        UserDetails userInstance = session.userDetails;
        if (userInstance != null) {
            String uid = userInstance.getUsername();
            render(view: "changePassword", model: [uid: uid])
        } else redirect(controller: "AccessControlException", action: "notLoggedIn")
    }


    def forgotPassword() {
        render(view: "resetPassword")
    }

    def synchronizeWithADPassword() {
        render(view: "synchronizeWithADPwd")
    }


    def checkPasswordStrength() {
        String pwd = params.password;
        PasswordChecker pwdStr = PasswordChecker.getInstance();
        String pwdLevel = pwdStr.checkPasswordStrength(pwd);
        render "Your password strength is ${pwdLevel}"
    }

    /*
* Change password with a new one, user must know the old password
* */

    def changeLdapUserAccountPassword(String uid) {
        UserDetails userInstance = session.userDetails;
        String oldPassword = (String) params.oldPassword;
        String newPassword = (String) params.newPassword;
        String confNewPassword = params.confNewPassword;
        PasswordChecker pwdStr = PasswordChecker.getInstance();
        int pwdStrengthLevel = pwdStr.checkPasswordStrengthInInt(newPassword);


       /*In case of old password equals new password, we do nothing*/
        if(oldPassword==newPassword){
            accessLogService.saveLog("Change password failed, new password is the same as old password", userInstance.username, LogType.Info)
            flash.error = "Your old password and new password is the same. Please enter a new one."
            redirect(action: "changePassword")
        }
     // The password is too weak, we do nothing
        else if (pwdStrengthLevel < BUMPConfig.passwordStrength) {
            accessLogService.saveLog("Change password failed, password is too weak", userInstance.username, LogType.Info)
            flash.error = "Your password is too weak! Please choose a strong one."
            redirect(action: "changePassword")
        }
        //Test if the confirm password equals the new password
        else if (newPassword == confNewPassword) {

                int result = memberManagerService.modifyUserPasswordWithUID(ldapConf, uid, newPassword, oldPassword);

                //Test the result of the change password, if success, redirect to the index with a success message
                switch (result) {
                    case 0: accessLogService.saveLog("Change password failed, Ldap server is not responding", userInstance.username, LogType.Error);
                        flash.error = "The server is not responding, your password cannot be changed. Please try later.";
                        redirect(action: "changePassword");
                        break;
                    case 1: List<String> msgList=new ArrayList<String>();
                        msgList.add("Your password has been modified.");
                        msgList.add("Try to logout and re-login to test your new password.")
                        log.info(AccessLogMessage.getChangePwdMessage(uid));
                        accessLogService.saveLog(AccessLogMessage.getChangePwdMessage(uid), userInstance.username, LogType.Info);
                        render(view: "changePwdResult",model:[messageList:msgList]);
                        break;
                    case 2: accessLogService.saveLog("Change password failed, account name does not exist or password is wrong", userInstance.username, LogType.Info);
                        flash.error = "Your account name does not exist or password is wrong, contact your admin for more information.";
                        redirect(action: "changePassword");
                        break;
                    default: accessLogService.saveLog("Change password failed, account name does not exist or password is wrong", userInstance.username, LogType.Info);
                        flash.error = "Your account name does not exist or password is wrong, contact your admin for more information.";
                        redirect(action: "changePassword");
                        break;
                }


            } else {
                accessLogService.saveLog("Change password failed, confirm new password and new password are different", userInstance.username, LogType.Info)
                flash.error = "Your confirm password and password are different. Please try again"
                redirect(action: "changePassword")
            }
        }




    //This method allow user who forget user password to reset a random password. Then they can use this to change their own password.
    //To reset the password, the user needs to enter uid and email. if the uid or email is not registered ,the reset request will be refused.
      def resetLdapUserAccountPassword(){


        String uid=(String)params.uid;
        String email= (String)params.mail;

          boolean flag=memberManagerService.checkUserUidWithMail(ldapConf,uid,email)

        def clientIp=request.getRemoteAddr();

        accessLogService.saveLog(clientIp+" wants to reset password of "+uid,clientIp,LogType.Info)
        if(!flag){
            flash.error="The uid or email which you entered does not exist. If you try to hack us, beware that your IP address is logged."
            accessLogService.saveLog(clientIp+" reset password of "+uid+" failed, uid or email doesnot exist",clientIp,LogType.Info)
            // uid wrong, redirect reset password page
            render(view:"resetPassword")
        }
        else {

                RandomPwdGenerator rpg=RandomPwdGenerator.getInstance();

                String pwd=rpg.generateRandomPassword();
                //reset password in ldap
                int result=memberManagerService.modifyUserPasswordWithUIDWithoutPwd(ldapConf,uid,pwd)
                //Reset password succeful redirect to user space page
                if(result==1){
                    List<String> msgList=new ArrayList<String>();

                    //Send new password to user mail box
                    MailManager emailer= MailManager.getInstance();
                    MailMessage mailMessage=MailMessage.getInstance();
                    String msg=mailMessage.getResetPasswordMessage("bioaster", uid,pwd);
                    try {
                        emailer.sendMail(email, "Password reset notification", msg);
                        accessLogService.saveLog(clientIp+" reset the password of "+uid+" successfully",clientIp,LogType.Info)
                        msgList.add("Your password has been reset by BUMP.");
                        msgList.add("you will receive your new temporal password by email.")
                        msgList.add("The generated password is not safe for long term use, please change it as soon as possible")
                        render(view: "changePwdResult",model:[messageList:msgList]);
                    } catch (MessagingException e) {
                        log.error("unable to send user the reset password notification email in controller: BioasterEndUserSpaceManager, action :resetLdapUserAccountPassword. Java exception "+e.toString());
                        flash.error = "Unable to send your new password to your email. Please contact the admin user."
                        render(view:"resetPassword")
                    }

                }
            else{
                    flash.error= "Your password can't be reset, Please contact the webmaster or your team leader";
                    accessLogService.saveLog(clientIp+" failed to reset the password of "+uid,clientIp,LogType.Error)
                    redirect(action:"resetPassword")
                }

            }
      }

    def synchronizeLdapPwdWithADPwd(){
        String uid=(String)params.uid;
        String password=(String)params.password;
        def clientIp=request.getRemoteAddr();
         int result=memberManagerService.syncUserPwdWithAD(ldapConf,uid,password);

        if(result==1){
            List<String> msgList=new ArrayList<String>();
            msgList.add("Your password has been synchronized with your AD password.");
            msgList.add("Try to logout and re-login to test your AD password.")
            accessLogService.saveLog(clientIp+" reset the password of "+uid+" successfully",clientIp,LogType.Info)
            render(view: "changePwdResult",model:[messageList:msgList]);
        }
        else{
            flash.error="Your password can't be reset, The login or password you entered is wrong."
            accessLogService.saveLog(clientIp+" failed to reset the password of "+uid,clientIp,LogType.Error)
            redirect(action:"synchronizeWithADPassword")
        }

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