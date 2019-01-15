package org.etriks.security.admin
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
import org.etriks.security.register.AbiriskMemberAccount
import org.etriks.security.registration.RegistrationValidatorService
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.transaction.annotation.Transactional

import javax.mail.MessagingException

@Transactional
class AbiriskRegistrationRequestValidationController {

    static final projectName="abirisk";
    def PubLdapConnectorConfig pubLdap=new PubLdapConnectorConfig();
    def AccessLogService accessLogService;
    def CreateNewUserAccountService createNewUserAccountService;

    // Create the instance of registration validate service
    def RegistrationValidatorService registrationValidatorService=RegistrationValidatorService.getInstance();

    def index() {}

    def abiriskAccountManager(){

        PolicyEngine pe=new PolicyEngine();
        if (session.userDetails==null){redirect(controller:"AccessControlException",action:"notLoggedIn")}
        else {
            def  userRoles=session.userDetails.getRole();
            if(pe.checkRoles("Role_AbiriskAdmin",userRoles)){
                def cZARabiriskMemberAccountInstanceList=AbiriskMemberAccount.list(params);

                def iterator= cZARabiriskMemberAccountInstanceList.iterator();
                while(!cZARabiriskMemberAccountInstanceList.isEmpty()&&iterator.hasNext()){
                    def abiriskMemberAccountInstance=iterator.next();
                    if (abiriskMemberAccountInstance.getAbiriskValidatorDecision().equals(true)){
                        cZARabiriskMemberAccountInstanceList.remove(abiriskMemberAccountInstance);
                    }
                }
                render (view:"abiriskCZARUserAccountManager",model:[abiriskMemberAccountInstanceList: cZARabiriskMemberAccountInstanceList ])
            }

            else {

                render (view: "abiriskUserAccountManager", model:[abiriskMemberAccountInstanceList: AbiriskMemberAccount.list(params) ])}

        }
    }

    def show(Long id) {
        def abiriskMemberAccountInstance = AbiriskMemberAccount.get(id)
        if (!abiriskMemberAccountInstance) {
            flash.message = message(code: 'default.not.found.message', args:'abiriskMemberAccount')
            redirect(action: "list")
            return
        }

        else {
            PolicyEngine pe=new PolicyEngine();
            def  userRoles=session.userDetails.getRole();
            if(pe.checkRoles("Role_AbiriskAdmin",userRoles)){
                render (view:"cZARShow",model: [abiriskMemberAccountInstance: abiriskMemberAccountInstance])
            }
            else {render (view:"show",model:[abiriskMemberAccountInstance: abiriskMemberAccountInstance])}

        }


    }

    def cZARValidation(Long id){
        def List<String> messageList=new ArrayList<String>();
        def abiriskMemberAccountInstance = AbiriskMemberAccount.get(id);

        abiriskMemberAccountInstance.setAbiriskValidatorDecision(true);

        if(abiriskMemberAccountInstance.getAbiriskValidatorDecision().equals(true)) {
            /************************************After the project validation, the hosting validator receives a validation mail************/
            def projectValidator=abiriskMemberAccountInstance.getAbiriskValidator().toString();
            def hostingValidator=abiriskMemberAccountInstance.getHostingValidator().toString();
            def userName=abiriskMemberAccountInstance.getFirstName()+" "+abiriskMemberAccountInstance.getLastName()

            CCJAutoMail emailer=new CCJAutoMail();
            def hostingValidatorEmail=registrationValidatorService.getHostingValidatorEmail(hostingValidator);
            String msg=new MailMessage().getRequestValidatedByCZARMessage("Abirisk",projectValidator,userName);
            emailer.sendMail(hostingValidatorEmail,"Abirisk User registration request validation notification",msg)


            /******************************/
            messageList.add("The registration request of "+abiriskMemberAccountInstance.firstName+" "+abiriskMemberAccountInstance.lastName+" has been validated");
            messageList.add("The user account will be added to the LDAP server shortly! ");
            messageList.add("You will receive a confirmation email, when it's done. ");
            accessLogService.saveLog("The registration request of "+abiriskMemberAccountInstance.firstName+" "+abiriskMemberAccountInstance.lastName+" has been validated",session.userDetails.username,ProjectId.abirisk,LogType.Info);
        }
        else {

            messageList.add("The registration request of "+abiriskMemberAccountInstance.firstName+" "+abiriskMemberAccountInstance.lastName+"can not be validated");

            messageList.add("Please contact admin@etriks.org to report this problem! ");
            accessLogService.saveLog("The registration request of "+abiriskMemberAccountInstance.firstName+" "+abiriskMemberAccountInstance.lastName+" cannot be validated",session.userDetails.username,ProjectId.abirisk,LogType.Error);
        }


        render (view:"cZARValidationResult",model:[messageList:messageList])
    }

/*
* This action retrive the abiriskMemberAccount from the database and render it on the ldapUserAccountEntry.gsp page
* */

    def ldapUserAccountEntry(Long id){

        def abiriskMemberAccountInstance = AbiriskMemberAccount.get(id)
        if (!abiriskMemberAccountInstance) {
            flash.message = message(code: 'default.not.found.message', args:'AbiriskMemberAccount')
            redirect(action: "list")
            return
        }

        /***********************New ldap entry preparation********************************/
        def abiriskMemberAccount=createNewUserAccountService.createNewAbiriskAccountPreparation(pubLdap,abiriskMemberAccountInstance)

      /*  def newAccountParam=createNewUserAccountService.userParamWrapper(abiriskNewMemberAccount)
        *//*************Insert into the new ldap server******************************************//*
        createNewUserAccountService.createNewProjectUserAccountLdapEntry(pubLdap,newAccountParam,"abirisk")*/

        log.info(LogMessages.getRequestViewedMessage(session.userDetails.username,abiriskMemberAccount.get("cn")));
        accessLogService.saveLog(LogMessages.getRequestViewedMessage(session.userDetails.username,abiriskMemberAccount.get("cn")),session.userDetails.username, ProjectId.abirisk,LogType.Info);

        [abiriskMemberAccount:  abiriskMemberAccount]

    }









    /*
   * This action add the abiriskMemberAccount into the Ldap server. It uses the src/java/org.etriks.security.ldap.entry.UserAccountManager.
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
            accessLogService.saveLog(LogMessages.getRequestValidatedMessage(session.userDetails.username,params.cn),session.userDetails.username, ProjectId.abirisk,LogType.Info)


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

            List<String> allMails=registrationValidatorService.getAllAbiriskValidatorEmails();
            for(int i=0;i<allMails.size();i++){
                String email= allMails.get(i);
                ccEmailer.sendMail(email,"Abirisk Account validation confirmation ", adminMSG)
            }

            /********************End**********************************/

            flash.message="The user account ${params.uid} has been added into the Ldap server";
            redirect(action:"abiriskAccountManager")
        }
        else {

            flash.message="The user account ${params.uid} already exits, it can not be added to the ldap server. Please contact the admin@etriks.org!!!"
            accessLogService.saveLog("Account creation failed, The user account ${params.uid} already exits",session.userDetails.username,ProjectId.abirisk,LogType.Error);
            redirect(action:"abiriskAccountManager")
        }


    }

    def edit(Long id) {
        def abiriskMemberAccountInstance = AbiriskMemberAccount.get(id)
        if (!abiriskMemberAccountInstance) {
            flash.message = message(code: 'default.not.found.message', args:'AbiriskMemberAccount')
            redirect(action: "list")
            return
        }

        [abiriskMemberAccountInstance: abiriskMemberAccountInstance]
    }

    /*def update(Long id, Long version) {
        def abiriskMemberAccountInstance = AbiriskMemberAccount.get(id)
        if (!abiriskMemberAccountInstance) {
            flash.message = message(code: 'default.not.found.message', args:'EtriksMemberAccount')
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (abiriskMemberAccountInstance.version > version) {
                abiriskMemberAccountInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'abiriskMemberAccount.label', default: 'abiriskMemberAccount')] as Object[],
                        "Another user has updated this abiriskMemberAccount while you were editing")
                render(view: "edit", model: [etriksMemberAccountInstance: abiriskMemberAccountInstance])
                return
            }
        }

        abiriskMemberAccountInstance.properties = params

        if (!abiriskMemberAccountInstance.save(flush: true)) {
            render(view: "edit", model: [abiriskMemberAccountInstance: abiriskMemberAccountInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args:'abiriskMemberAccount')
        redirect(action: "show", id: abiriskMemberAccountInstance.id)
    }*/

    def clear(Long id){
        def abiriskMemberAccountInstance = AbiriskMemberAccount.get(id)
        if (!abiriskMemberAccountInstance) {
            flash.message = "The abirisk registration request "+id+" has errors"
            redirect(action: "abiriskAccountManager")
            return
        }
        try {
            abiriskMemberAccountInstance.delete(flush: true)
            flash.message = "The registration request" +id+"has been deleted !!!";
        }
        catch (DataIntegrityViolationException e) {
            flash.message = "Cannot delete this abirisk registration request, please contact admin@etriks.org"
            redirect(action: "show", id: id)
        }
    }

    def delete(Long id) {
        def abiriskMemberAccountInstance = AbiriskMemberAccount.get(id)
        if (!abiriskMemberAccountInstance) {
            flash.message = "The abirisk registration request "+id+" has errors"
            redirect(action: "abiriskAccountManager")
            return
        }

        try {
            abiriskMemberAccountInstance.delete(flush: true)
            flash.message = "The registration request " +id+" has been deleted !!!";
            def String validator=abiriskMemberAccountInstance.abiriskValidator;
            String deletionMail=new MailMessage().getRequestDeletionMessage("eTRIKS project",validator);
            CCJAutoMail Emailer=new CCJAutoMail();
            def String userMail=abiriskMemberAccountInstance.email;
            try {
                Emailer.sendMail(userMail,"Your account creation request has been deleted!!!",deletionMail);
            } catch (MessagingException e) {
                log.error("In etriks account request deletion, Unable to send user notification email. Java exception : "+e.toString());
            }
            //log the action of the validator
            log.info(LogMessages.getRequestDeletedMessage(session.userDetails.username,abiriskMemberAccountInstance.firstName,abiriskMemberAccountInstance.lastName,abiriskMemberAccountInstance.email));
           accessLogService.saveLog(LogMessages.getRequestDeletedMessage(session.userDetails.username,abiriskMemberAccountInstance.firstName,abiriskMemberAccountInstance.lastName,abiriskMemberAccountInstance.email),session.userDetails.username,ProjectId.abirisk,LogType.Info);
            redirect(action: "abiriskAccountManager")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = "Cannot delete this abirisk registration request, please contact admin@etriks.org"
            redirect(action: "show", id: id)
        }
    }

    def checkPrivacyTraining(Long id){
        def validatorAgreePrivacyTraining=params.get("validatorAgreePrivacyTraining");
        def abiriskMemberAccountInstance = AbiriskMemberAccount.get(id);

        if (validatorAgreePrivacyTraining.equals("true")){
            accessLogService.saveLog(LogMessages.getDataPrivacyTrainingAgreementMessage(session.userDetails.username,abiriskMemberAccountInstance.firstName,abiriskMemberAccountInstance.lastName),session.userDetails.username,ProjectId.abirisk,LogType.Info)
            redirect(action:"cZARValidation",id: id);
        }
        else{
            flash.message="You have to provide data privacy portection training to this user first!";
            redirect (action:"show",id:id);
        }
    }
}

