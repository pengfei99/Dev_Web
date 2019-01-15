package org.etriks.security.admin
import grails.transaction.Transactional
import org.etriks.log.LogMessages
import org.etriks.log.LogType
import org.etriks.log.ProjectId
import org.etriks.mail.CCJAutoMail
import org.etriks.mail.MailMessage
import org.etriks.security.Ldap.CreateNewUserAccountService
import org.etriks.security.encryption.PasswordEncrytor
import org.etriks.security.ldapnew.serverprofile.PubLdapConnectorConfig
import org.etriks.security.log.AccessLogService
import org.etriks.security.register.EtriksMemberAccount
import org.springframework.dao.DataIntegrityViolationException

import javax.mail.MessagingException
/* This class allow admin to validate etriks user registration request*/
@Transactional
class RegistrationRequestValidationController {


    static final projectName="etriks";
    def AccessLogService accessLogService;
    def CreateNewUserAccountService createNewUserAccountService;

    //The new ldap config for all members
    def PubLdapConnectorConfig pubLdap=new PubLdapConnectorConfig();

    def index() {}

    def userAccountManager(){
        render(view: "userAccountManager", model:[etriksMemberAccountInstanceList: EtriksMemberAccount.list(params) ])
    }

    def show(Long id) {
        def etriksMemberAccountInstance = EtriksMemberAccount.get(id)
        if (!etriksMemberAccountInstance) {
            flash.message = message(code: 'default.not.found.message', args:'EtriksMemberAccount')
            redirect(action: "list")
            return
        }

        [etriksMemberAccountInstance: etriksMemberAccountInstance]
    }

/*
* This action retrive the etriksMemberAccount from the database and render it on the ldapUserAccountEntry.gsp page
* */

    def ldapUserAccountEntry(Long id){

        def etriksMemberAccountInstance = EtriksMemberAccount.get(id)
         if (!etriksMemberAccountInstance) {
           flash.message = message(code: 'default.not.found.message', args:'EtriksMemberAccount')
           redirect(action: "list")
           return
       }


        /***********************New ldap entry preparation********************************/
        def etriksMemberAccount=createNewUserAccountService.createNewEtriksAccountPreparation(pubLdap,etriksMemberAccountInstance)



        log.info(LogMessages.getRequestViewedMessage(session.userDetails.username,etriksMemberAccount.get("cn")));
        accessLogService.saveLog(LogMessages.getRequestViewedMessage(session.userDetails.username,etriksMemberAccount.get("cn")),session.userDetails.username, ProjectId.etriks,LogType.Info);
        [etriksMemberAccount:etriksMemberAccount]

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
        String cn=params.cn;
        String uid=params.uid;
        String sn=params.sn;
        String uidNumber=params.uidNumber;
        String gidNumber=params.gidNumber;
        String homeDirectory=params.homeDirectory;
        String loginShell=params.loginShell;
        String mail=params.mail;
        String givenName=params.givenName;
        String workPackageCN="cn="+params.workPackage;
        paramMap.put("cn",cn);
        paramMap.put("uid",uid);
        paramMap.put("sn",sn);
        paramMap.put("uidNumber",uidNumber);
        paramMap.put("gidNumber",gidNumber);
        paramMap.put("homeDirectory",homeDirectory);
        paramMap.put("loginShell",loginShell);
        paramMap.put("mail",mail);
        paramMap.put("givenName",givenName);
        paramMap.put("userPassword",userPassword);
        paramMap.put("workPackageCN",workPackageCN);

/****************************Insert into the oldLdap server***********************/
        def flag=createNewUserAccountService.createNewEtriksUserAccountLdapEntry(pubLdap,paramMap)


        if (flag==true){
            //log the request validated
            log.info(LogMessages.getRequestValidatedMessage(session.userDetails.username,params.cn));
            accessLogService.saveLog(LogMessages.getRequestValidatedMessage(session.userDetails.username,params.cn),session.userDetails.username, ProjectId.etriks,LogType.Info)
            //delete the registration request from the data base
            clear(id);
            //Send mail to Cient
            CCJAutoMail emailer = new CCJAutoMail();
            //the domains of these email addresses should be valid, or it will fail
            String msg=new MailMessage().getAccountActivationMessage(params.uid);
            emailer.sendMail(params.mail,"Account validation confirmation ", msg);

            flash.message="The user account ${params.uid} has been added into the Ldap server";
            redirect(action:"userAccountManager")
        }
        else {

            flash.message="Something is wrong, The user account ${params.uid} can not be added to the ldap server. Please contact admin@etriks.org"
            accessLogService.saveLog("Account creation failed, The user account ${params.uid} already exits",session.userDetails.username,ProjectId.etriks,LogType.Error);
            redirect(action:"userAccountManager")
        }

    }


    def update(Long id, Long version) {
        def etriksMemberAccountInstance = EtriksMemberAccount.get(id)
        if (!etriksMemberAccountInstance) {
            flash.message = message(code: 'default.not.found.message', args:'EtriksMemberAccount')
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (etriksMemberAccountInstance.version > version) {
                etriksMemberAccountInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message( default: 'EtriksMemberAccount')] as Object[],
                        "Another user has updated this EtriksMemberAccount while you were editing")
                render(view: "edit", model: [etriksMemberAccountInstance: etriksMemberAccountInstance])
                return
            }
        }

        etriksMemberAccountInstance.properties = params

        if (!etriksMemberAccountInstance.save(flush: true)) {
            render(view: "edit", model: [etriksMemberAccountInstance: etriksMemberAccountInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args:'EtriksMemberAccount')
        redirect(action: "show", id: etriksMemberAccountInstance.id)
    }

    def clear(Long id){
        def etriksMemberAccountInstance = EtriksMemberAccount.get(id);
        if (!etriksMemberAccountInstance) {
            flash.message = message(code: 'default.not.found.message', args:'EtriksMemberAccount')
            redirect(action: "userAccountManager")
            return
        }

        try {
            etriksMemberAccountInstance.delete(flush: true)
            flash.message = "The registration request" +id+"has been deleted !!!";
        }

        catch (DataIntegrityViolationException e) {
            flash.message = "Cannot delete this etriks registration request, please contact admin@etriks.org"
            redirect(action: "show", id: id)
        }



    }

    def delete(Long id) {
        def etriksMemberAccountInstance = EtriksMemberAccount.get(id)
        if (!etriksMemberAccountInstance) {
            flash.message = message(code: 'default.not.found.message', args:'EtriksMemberAccount')
            redirect(action: "userAccountManager")
            return
        }

        try {
            etriksMemberAccountInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message( default: 'EtriksMemberAccount'), id])
             def String validator=etriksMemberAccountInstance.validator;
            String deletionMail=new MailMessage().getRequestDeletionMessage("eTRIKS project",validator);
            CCJAutoMail Emailer=new CCJAutoMail();
            def String userMail=etriksMemberAccountInstance.email;
            try {
                Emailer.sendMail(userMail,"Your account creation request has been deleted!!!",deletionMail);
            } catch (MessagingException e) {
                log.error("In etriks account request deletion, Unable to send user notification email. Java exception : "+e.toString());
            }
            log.info(LogMessages.getRequestDeletedMessage(session.userDetails.username,etriksMemberAccountInstance.firstName,etriksMemberAccountInstance.lastName,etriksMemberAccountInstance.email));
            accessLogService.saveLog(LogMessages.getRequestDeletedMessage(session.userDetails.username,etriksMemberAccountInstance.firstName,etriksMemberAccountInstance.lastName,etriksMemberAccountInstance.email),session.userDetails.username,ProjectId.etriks,LogType.Info);
            redirect(action: "userAccountManager")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message( default: 'EtriksMemberAccount'), id])
            redirect(action: "show", id: id)
        }
    }

    def checkPrivacyTraining(Long id){
            def validatorAgreePrivacyTraining=params.get("validatorAgreePrivacyTraining");
        def etriksMemberAccountInstance = EtriksMemberAccount.get(id);
        if (validatorAgreePrivacyTraining.equals("true")){
            accessLogService.saveLog(LogMessages.getDataPrivacyTrainingAgreementMessage(session.userDetails.username,etriksMemberAccountInstance.firstName,etriksMemberAccountInstance.lastName),session.userDetails.username,ProjectId.etriks,LogType.Info)
            redirect(action:"ldapUserAccountEntry",id: id);
        }
        else{
            flash.message="You have to provide data privacy portection training to this user first!";
            redirect (action:"show",id:id);
        }
    }
}
