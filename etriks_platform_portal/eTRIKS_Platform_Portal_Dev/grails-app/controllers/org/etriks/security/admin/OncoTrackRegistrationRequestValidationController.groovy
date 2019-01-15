package org.etriks.security.admin
import grails.transaction.Transactional
import org.etriks.log.LogMessages
import org.etriks.log.LogType
import org.etriks.log.ProjectId
import org.etriks.mail.CCJAutoMail
import org.etriks.mail.MailMessage
import org.etriks.security.Ldap.CreateNewUserAccountService
import org.etriks.security.authorization.PolicyEngine
import org.etriks.security.encryption.PasswordEncrytor
import org.etriks.security.ldapnew.serverprofile.PubLdapConnectorConfig
import org.etriks.security.log.AccessLogService
import org.etriks.security.register.OncoTrackMemberAccount
import org.etriks.security.registration.RegistrationValidatorService
import org.springframework.dao.DataIntegrityViolationException

import javax.mail.MessagingException

@Transactional
class OncoTrackRegistrationRequestValidationController {

    static final projectName="oncotrack";
    def PubLdapConnectorConfig pubLdap=new PubLdapConnectorConfig();
    def AccessLogService accessLogService;
    def CreateNewUserAccountService createNewUserAccountService;

    // Create the instance of registration validate service
    def RegistrationValidatorService registrationValidatorService=RegistrationValidatorService.getInstance();


    def index() {}

    def oncoTrackAccountManager(){
        PolicyEngine pe=new PolicyEngine();
        if (session.userDetails==null){redirect(controller:"AccessControlException",action:"notLoggedIn")}
        else {
        def  userRoles=session.userDetails.getRole();
        if(pe.checkRoles("Role_OncoTrackAdmin",userRoles)){
            def cZARoncoTrackMemberAccountInstanceList=OncoTrackMemberAccount.list(params);

           def iterator= cZARoncoTrackMemberAccountInstanceList.iterator();
            while(!cZARoncoTrackMemberAccountInstanceList.isEmpty()&&iterator.hasNext()){
                def oncoTrackMemberAccountInstance=iterator.next();
                if (oncoTrackMemberAccountInstance.getOncoTrackValidatorDecision().equals(true)){
                    cZARoncoTrackMemberAccountInstanceList.remove(oncoTrackMemberAccountInstance);
                }
            }
            render (view:"oncoTrackCZARUserAccountManager",model:[oncoTrackMemberAccountInstanceList: cZARoncoTrackMemberAccountInstanceList ])
        }
        else {

            render(view: "oncoTrackUserAccountManager", model:[oncoTrackMemberAccountInstanceList: OncoTrackMemberAccount.list(params) ])}

    }
    }

    def show(Long id) {
        def oncoTrackMemberAccountInstance = OncoTrackMemberAccount.get(id)
        if (!oncoTrackMemberAccountInstance) {
            flash.message = message(code: 'default.not.found.message', args:'oncoTrackMemberAccount')
            redirect(action: "list")
            return
        }

        else {
            PolicyEngine pe=new PolicyEngine();
            def  userRoles=session.userDetails.getRole();
            if(pe.checkRoles("Role_OncoTrackAdmin",userRoles)){
                render (view:"cZARShow",model: [oncoTrackMemberAccountInstance: oncoTrackMemberAccountInstance])
            }
            else {render (view:"show",model:[oncoTrackMemberAccountInstance: oncoTrackMemberAccountInstance])}

        }


    }

    def cZARValidation(Long id){
        def List<String> messageList=new ArrayList<String>();
        def oncoTrackMemberAccountInstance = OncoTrackMemberAccount.get(id);

        oncoTrackMemberAccountInstance.setOncoTrackValidatorDecision(true);

        if(oncoTrackMemberAccountInstance.getOncoTrackValidatorDecision().equals(true)) {
            /************************************After the project validation, the hosting validator receives a validation mail************/
          def hostingValidator=oncoTrackMemberAccountInstance.getHostingValidator().toString();
            def userName=oncoTrackMemberAccountInstance.getFirstName()+" "+oncoTrackMemberAccountInstance.getLastName()
            CCJAutoMail emailer=new CCJAutoMail();

            def hostingValidatorEmail=registrationValidatorService.getHostingValidatorEmail(hostingValidator);
            String msg=new MailMessage().getRequestValidatedByCZARMessage("OncoTrack",hostingValidator,userName);
            emailer.sendMail(hostingValidatorEmail,"OncoTrack User registration request validation notification",msg)


            /******************************/
            messageList.add("The registration request of "+oncoTrackMemberAccountInstance.firstName+" "+oncoTrackMemberAccountInstance.lastName+" has been validated");
            messageList.add("The user account will be added to the LDAP server shortly! ");
            messageList.add("You will receive a confirmation email, when it's done. ");
            accessLogService.saveLog("The registration request of "+oncoTrackMemberAccountInstance.firstName+" "+oncoTrackMemberAccountInstance.lastName+" has been validated",session.userDetails.username,ProjectId.oncoTrack,LogType.Info);
        }
        else {
            messageList.add("The registration request of "+oncoTrackMemberAccountInstance.firstName+" "+oncoTrackMemberAccountInstance.lastName+"can not be validated");
            messageList.add("Please contact admin@etriks.org to report this problem! ");
            accessLogService.saveLog("The registration request of "+oncoTrackMemberAccountInstance.firstName+" "+oncoTrackMemberAccountInstance.lastName+" cannot be validated",session.userDetails.username,ProjectId.oncoTrack,LogType.Error);
        }


        render (view:"cZARValidationResult",model:[messageList:messageList])
    }

/*
* This action retrive the etriksMemberAccount from the database and render it on the ldapUserAccountEntry.gsp page
* */

    def ldapUserAccountEntry(Long id){


        def oncoTrackMemberAccountInstance = OncoTrackMemberAccount.get(id)
        if (!oncoTrackMemberAccountInstance) {
            flash.message = message(code: 'default.not.found.message', args:'OncoTrackMemberAccount')
            redirect(action: "list")
            return
        }


        /***********************New ldap entry preparation********************************/
        def oncoTrackMemberAccount=createNewUserAccountService.createNewOncotrackAccountPreparation(pubLdap,oncoTrackMemberAccountInstance)

        log.info(LogMessages.getRequestViewedMessage(session.userDetails.username,oncoTrackMemberAccount.get("cn")));
        accessLogService.saveLog(LogMessages.getRequestViewedMessage(session.userDetails.username,oncoTrackMemberAccount.get("cn")),session.userDetails.username, ProjectId.oncoTrack,LogType.Info);

        [oncoTrackMemberAccount:  oncoTrackMemberAccount]
    }





    /*
   * This action add the EtriksMemberAccount into the Ldap server. It uses the src/java/org.etriks.security.ldap.entry.UserAccountManager.
   *
   * */

    def createLdapEntry(Long id){

        def LinkedHashMap<String,Object> paramMap=new LinkedHashMap<String,Object>();

        //create the instance of the password encrypter.
        PasswordEncrytor pwdEncrypter=new PasswordEncrytor();
        //Encrypte the password,
        String userPassword=pwdEncrypter.digestBase64("sha", params.userPassword);
        String validator=params.validator;

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
        paramMap.put("groups",params.groups)

/****************************Insert into the oldLdap server***********************/
        def flag=createNewUserAccountService.createNewProjectUserAccountLdapEntry(pubLdap,paramMap,projectName)


        if (flag==true){
            //Remove the registration request from the database

            clear(id);
            log.info(LogMessages.getRequestValidatedMessage(session.userDetails.username,params.cn));
            accessLogService.saveLog(LogMessages.getRequestValidatedMessage(session.userDetails.username,params.cn),session.userDetails.username, ProjectId.oncoTrack,LogType.Info);


            /***************************************************************
            * Send the user a mail to inform him, his account has been validated
            ***************************************************************/
            CCJAutoMail emailer = new CCJAutoMail();
            //the domains of these email addresses should be valid, or it will fail
            String msg=new MailMessage().getAccountActivationMessage(params.uid);

            emailer.sendMail(params.mail,"Account validation confirmation ", msg);

            /********************End**********************************/

            /***************************************************************
             * Send all the admin user a mail to inform them, an account has been validated by a validator
             ***************************************************************/
           String adminMSG=new MailMessage().getAccountCreationConfirmationMessage(params.uid,validator);
           CCJAutoMail ccEmailer=new CCJAutoMail();


            List<String> allMails=registrationValidatorService.getAllOncotrackValidatorEmails();
            for(int i=0;i<allMails.size();i++){
               String email= allMails.get(i);
                //System.out.println("admin mail"+email);
                ccEmailer.sendMail(email,"OncoTrack Account validation confirmation ", adminMSG)
            }

            /********************End**********************************/

            flash.message="The user account ${params.uid} has been added into the Ldap server";
            redirect(action:"oncoTrackAccountManager")
        }
        else {
            accessLogService.saveLog("Account creation failed, The user account ${params.uid} already exits",session.userDetails.username,ProjectId.oncoTrack,LogType.Error);
            flash.message="The user account ${params.uid} already exits, it can not be added to the ldap server. Please contact the admin@etriks.org!!!"
            redirect(action:"oncoTrackAccountManager")
        }


    }

    def edit(Long id) {
        def oncoTrackMemberAccountInstance = OncoTrackMemberAccount.get(id)
        if (!oncoTrackMemberAccountInstance) {
            flash.message = message(code: 'default.not.found.message', args:'EtriksMemberAccount')
            redirect(action: "list")
            return
        }

        [etriksMemberAccountInstance: oncoTrackMemberAccountInstance]
    }

    def clear(Long id){
        def oncoTrackMemberAccountInstance = OncoTrackMemberAccount.get(id)
        if (!oncoTrackMemberAccountInstance) {
            flash.message = "The oncoTrack registration request "+id+" has errors"

            redirect(action: "oncoTrackAccountManager")
            return
        }
        try {
            oncoTrackMemberAccountInstance.delete(flush: true)
            flash.message = "The registration request" +id+"has been deleted !!!";
    }
        catch (DataIntegrityViolationException e) {
            flash.message = "Cannot delete this oncoTrack registration request, please contact admin@etriks.org"
            redirect(action: "show", id: id)
        }
    }

    def delete(Long id) {
        def oncoTrackMemberAccountInstance = OncoTrackMemberAccount.get(id)
        if (!oncoTrackMemberAccountInstance) {
            flash.message = "The oncoTrack registration request "+id+" has errors"
            redirect(action: "oncoTrackAccountManager")
            return
        }

        try {
            oncoTrackMemberAccountInstance.delete(flush: true)
            flash.message = "The registration request " +id+" has been deleted !!!";
            def String validator=oncoTrackMemberAccountInstance.oncoTrackValidator;
            String deletionMail=new MailMessage().getRequestDeletionMessage("eTRIKS project",validator);
            CCJAutoMail Emailer=new CCJAutoMail();
            def String userMail=oncoTrackMemberAccountInstance.email;
            try {
                Emailer.sendMail(userMail,"Your account creation request has been deleted!!!",deletionMail);
            } catch (MessagingException e) {
                log.error("In etriks account request deletion, Unable to send user notification email. Java exception : "+e.toString());
            }
            //log the action of the validator
            log.info(LogMessages.getRequestDeletedMessage(session.userDetails.username,oncoTrackMemberAccountInstance.firstName,oncoTrackMemberAccountInstance.lastName,oncoTrackMemberAccountInstance.email));
            accessLogService.saveLog(LogMessages.getRequestDeletedMessage(session.userDetails.username,oncoTrackMemberAccountInstance.firstName,oncoTrackMemberAccountInstance.lastName,oncoTrackMemberAccountInstance.email),session.userDetails.username,ProjectId.oncoTrack,LogType.Info);
            redirect(action: "oncoTrackAccountManager")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = "Cannot delete this oncoTrack registration request, please contact admin@etriks.org"
            redirect(action: "show", id: id)
        }
    }

    def checkPrivacyTraining(Long id){
        def validatorAgreePrivacyTraining=params.get("validatorAgreePrivacyTraining");
        def oncoTrackMemberAccountInstance = OncoTrackMemberAccount.get(id);

        if (validatorAgreePrivacyTraining.equals("true")){
            accessLogService.saveLog(LogMessages.getDataPrivacyTrainingAgreementMessage(session.userDetails.username,oncoTrackMemberAccountInstance.firstName,oncoTrackMemberAccountInstance.lastName),session.userDetails.username,ProjectId.oncoTrack,LogType.Info);
            redirect(action:"cZARValidation",id: id);
        }
        else{
            flash.message="You have to provide data privacy portection training to this user first!";
            redirect (action:"show",id:id);
        }
    }
}
