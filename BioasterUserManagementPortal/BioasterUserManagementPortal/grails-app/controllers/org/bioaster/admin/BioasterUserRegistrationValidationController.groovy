package org.bioaster.admin

import org.bioaster.log.AccessLogMessage
import org.bioaster.log.AccessLogService
import org.bioaster.log.LogType
import org.bioaster.mail.MailManager
import org.bioaster.mail.MailManagerService
import org.bioaster.mail.MailMessage
import org.bioaster.registration.BioasterUserRegistrationRequest
import org.bioaster.security.UserRegistrationService
import org.bioaster.security.authorization.PolicyEngine
import org.bioaster.security.config.BUMPConfig
import org.bioaster.security.config.ILdapServerConnectorConfig
import org.bioaster.security.config.OpenLdapConfig
import org.bioaster.security.encryption.PasswordEncoder
import org.springframework.dao.DataIntegrityViolationException

import javax.mail.MessagingException

class BioasterUserRegistrationValidationController {

    AccessLogService accessLogService;
    UserRegistrationService userRegistrationService;
    MailManagerService mailManagerService;
    ILdapServerConnectorConfig ldapConf= OpenLdapConfig.getInstance();

    /*We check the role of the session user, if it's a validator, we show validator view, if it's a admin, we show admin view*/
    def index() {
       /*Integer max
       params.max = Math.min(max ?: 10, 100)
        respond BioasterUserRegistrationRequest.list(params), model:[bioasterUserRegistrationRequestCount: BioasterUserRegistrationRequest.count()]*/

        List<String> userRoles=(List<String>)session.userDetails.getRoles();

        /**/
        if(PolicyEngine.getInstance().checkRole(BUMPConfig.validatorRole,userRoles)){
            def requestListForValidatorView=BioasterUserRegistrationRequest.findAll("from BioasterUserRegistrationRequest as r where r.validatorDecision=false")
            render (view:"registrationListForValidator",model: [bioasterUserRegistrationRequestList: requestListForValidatorView])
        }
        else if(PolicyEngine.getInstance().checkRole(BUMPConfig.adminRole,userRoles)){
            def bioasterUserRegistrationRequestList=BioasterUserRegistrationRequest.list(params)
            render (view:"registrationListForAdmin",model:[bioasterUserRegistrationRequestList: bioasterUserRegistrationRequestList])
        }
    }

/*method called after admin user click on reuqester name to show request details*/
 def show(Long id) {
     def bioasterUserRegistrationRequest = BioasterUserRegistrationRequest.get(id)
     List<String> userRoles=(List<String>)session.userDetails.getRoles();
     if(PolicyEngine.getInstance().checkRole(BUMPConfig.validatorRole,userRoles)){
         render (view:"validatorShow",model: [bioasterUserRegistrationRequest: bioasterUserRegistrationRequest])
     }
     else if(PolicyEngine.getInstance().checkRole(BUMPConfig.adminRole,userRoles)){
         render (view:"adminShow",model:[bioasterUserRegistrationRequest: bioasterUserRegistrationRequest])
     }
     else redirect(controller:"accessControlException",action:"accessDeny")
}

    /*method called after the validator clicks on the validate request button*/
   def validatorValidation(Long id){
       List<String> msgList=new ArrayList<String>();
       def bioasterUserRegistrationRequest = BioasterUserRegistrationRequest.get(id)
       bioasterUserRegistrationRequest.setValidatorDecision(true);
       if(!bioasterUserRegistrationRequest.save(flush:true)) {
           msgList.add("The registration request of "+bioasterUserRegistrationRequest.firstName+" "+bioasterUserRegistrationRequest.lastName+"can not be validated");
           msgList.add("Please contact "+ BUMPConfig.supportMail+" to report this problem! ");
           accessLogService.saveLog("DB errors. The registration request of "+bioasterUserRegistrationRequest.firstName+" "+bioasterUserRegistrationRequest.lastName+" cannot be validated",(String)session.userDetails.username,LogType.Error);
       }
       /*If the registration request is validated, notified the admin, and redirect validator to success page*/
      else if(bioasterUserRegistrationRequest.validatorDecision){
           /*notify admin user*/
           List<String> adminMailList=userRegistrationService.getAdminMailList(ldapConf);
           MailManager mailManager=MailManager.getInstance();
           MailMessage mailMessage=MailMessage.getInstance();
           for(String adminMail: adminMailList){
               try {
                   mailManager.sendMail(adminMail,"User registration request validated",mailMessage.getRequestValidatedByValidatorMessage(bioasterUserRegistrationRequest.validator,bioasterUserRegistrationRequest.firstName+" "+bioasterUserRegistrationRequest.lastName))
               } catch (MessagingException e) {
                   log.error("unable to send validator validation notification to admin. Java exception "+e.toString());
               }

           }
              /*prepare the result page message*/
           msgList.add("The registration request of "+bioasterUserRegistrationRequest.firstName+" "+bioasterUserRegistrationRequest.lastName+" has been validated");
           msgList.add("The user account will be added to the LDAP server shortly! ");
           accessLogService.saveLog("The registration request of "+bioasterUserRegistrationRequest.firstName+" "+bioasterUserRegistrationRequest.lastName+" has been validated",(String)session.userDetails.username,LogType.Info);
       }
       else {
           msgList.add("The registration request of "+bioasterUserRegistrationRequest.firstName+" "+bioasterUserRegistrationRequest.lastName+"can not be validated");
           msgList.add("Please contact "+ BUMPConfig.supportMail+" to report this problem! ");
           accessLogService.saveLog("The registration request of "+bioasterUserRegistrationRequest.firstName+" "+bioasterUserRegistrationRequest.lastName+" cannot be validated",(String)session.userDetails.username,LogType.Error);

       }
       render (view:"validatorValidationResult",model:[messageList:msgList])
   }

    /*this method is called by admin show, after the admin user want to validate the request, it prepares all attrs of the requester and render them for final review, */
    def ldapUserAccountEntry(Long id){
        def bioasterUserRegistrationRequest = BioasterUserRegistrationRequest.get(id)
        if (!bioasterUserRegistrationRequest) {
            flash.message = "Can't find the user registration request which you asked"
            redirect(action: "index")
            return
        }

        /***********************Get the ldap account entry for requester********************************/
        def bioasterLdapAccountEntry=userRegistrationService.buildLdapEntryAttrs(ldapConf,bioasterUserRegistrationRequest)
        log.info(AccessLogMessage.getRequestViewedMessage((String)session.userDetails.username,(String)bioasterLdapAccountEntry.get("cn")));
        accessLogService.saveLog(AccessLogMessage.getRequestViewedMessage((String)session.userDetails.username,(String)bioasterLdapAccountEntry.get("cn")),(String)session.userDetails.username,LogType.Info);
        render (view:"createLdapEntry",model:[bioasterLdapAccountEntry:  bioasterLdapAccountEntry])


    }

    /*After the final review of the requester attrs, createLdapEntryview call this method to enter the requester account into ldap server*/
    def createLdapEntry(Long id){
      /*Get the attrs after final review*/
        Map<String,Object> paramMap=new LinkedHashMap<String,Object>();
        String userPassword = (String)params.userPassword;
        if(!userPassword.startsWith("{SSHA}")) {
            //create the instance of the password encrypter.
            PasswordEncoder pwdEncoder=new PasswordEncoder();
            //Encrypte the password,
            userPassword=pwdEncoder.encPassword(userPassword);
        }
        paramMap.put("cn",params.cn);
        paramMap.put("uid",params.uid);
        paramMap.put("sn",params.sn);
        paramMap.put("uidNumber",params.uidNumber);
        paramMap.put("gidNumber",params.gidNumber);
        paramMap.put("homeDirectory",params.homeDirectory);
        paramMap.put("loginShell",params.loginShell);
        paramMap.put("mail",params.mail);
        paramMap.put("givenName",params.givenName);
        paramMap.put("userPassword",userPassword);



        /*create user account in ldap server*/
        boolean result=userRegistrationService.createUserLdapEntry(ldapConf,paramMap)

        /*if the result is true, send notification mail to all admin, else show error message*/
        if(result){
           /*remove the request from db*/
            clear(id);
            log.info(AccessLogMessage.getRequestValidatedMessage((String)session.userDetails.username,(String)params.cn));
            accessLogService.saveLog(AccessLogMessage.getRequestValidatedMessage((String)session.userDetails.username,(String)params.cn),(String)session.userDetails.username,LogType.Info)
         /*send mail to request validator and admin  */
            String validatorEmail=userRegistrationService.getValidatorMail(ldapConf,(String)params.validator);
            List<String> adminEmailList=userRegistrationService.getAdminMailList(ldapConf);
            boolean mailSend=mailManagerService.sendRegistrationCompleteEmail((String)params.mail,(String)params.validator,validatorEmail,(String)params.uid,adminEmailList)
            if (mailSend){
                flash.message="User account has been created, requester, validator and admin are all notified."

            }
            else {flash.error="User account has been created, but failed to send confimation email to user and admin."
               }
        }
        else{
            flash.error="Failed to create user account, please check if DN already Exists. Contact "+BUMPConfig.supportMail+" for more information.";

        }
        redirect(action:"index")
    }

    def boolean clear(Long id){
        def bioasterUserRegistrationRequest = BioasterUserRegistrationRequest.get(id)
        if (!bioasterUserRegistrationRequest) {
            flash.error = "The bioaster user registration request " + id + " has errors"
            return false;
        }
        try {
            bioasterUserRegistrationRequest.delete(flush: true)
            flash.message = "The registration request" +id+"has been deleted.";
            return true;
        }
        catch (DataIntegrityViolationException e) {
            flash.message = "Cannot delete this bioaster registration request from BUMP Data Base, please contact admin@etriks.org"
            return false;

        }
    }

    def delete(Long id) {
        def bioasterUserRegistrationRequest = BioasterUserRegistrationRequest.get(id)
        if (!bioasterUserRegistrationRequest) {
            flash.error = "The bioaster user registration request " + id + " has errors"
            redirect(action: "index")
            return
        }

        try {
            bioasterUserRegistrationRequest.delete(flush: true)
            flash.message = "The registration request " + id + " has been deleted.";
            String validator = bioasterUserRegistrationRequest.validator;
            String msg = new MailMessage().getRequestDeletionMessage("BIOASTER", validator);
            MailManager mailManager = MailManager.getInstance();
            String userMail = bioasterUserRegistrationRequest.email;
            try {
                mailManager.sendMail(userMail, "Your registration request has been deleted.", msg);
            } catch (MessagingException e) {
                log.error("In registration request deletion, Unable to send user notification email. Java exception : " + e.toString());
            }
            //log the action of the validator
            log.info(AccessLogMessage.getRequestDeletedMessage((String) session.userDetails.username, bioasterUserRegistrationRequest.firstName, bioasterUserRegistrationRequest.lastName, bioasterUserRegistrationRequest.email));
            accessLogService.saveLog(AccessLogMessage.getRequestDeletedMessage((String) session.userDetails.username, bioasterUserRegistrationRequest.firstName, bioasterUserRegistrationRequest.lastName, bioasterUserRegistrationRequest.email), (String) session.userDetails.username, LogType.Info);
            redirect(action: "index")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = "Cannot delete this bioaster registration request, please contact admin@etriks.org"
            redirect(action: "show", id: id)
        }
    }


}

