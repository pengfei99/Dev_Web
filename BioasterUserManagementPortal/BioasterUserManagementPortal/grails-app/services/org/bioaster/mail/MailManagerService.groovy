package org.bioaster.mail

import grails.transaction.Transactional

import javax.mail.MessagingException

@Transactional
class MailManagerService {

    def mailer=MailManager.getInstance();
    def mailMessage=MailMessage.getInstance();

    def boolean sendRegistrationCompleteEmail(String userEmail,String validatorName,String validatorEmail,String userID,List<String> adminEmailList){

        def result=false;

        String adminMsg=mailMessage.getAccountCreationConfirmationMessage(userID,validatorName);
        String userMsg=mailMessage.getAccountActivationMessage(userID);

        try{
//Send emails to requester
            mailer.sendMail(userEmail,"Your registation for BIOASTER account has been accepted",userMsg)
            //Send emails to validator
            mailer.sendMail(validatorEmail,"New BIOASTER OPENLdap account has been created ",adminMsg);
            //Send emails to admin
            for(String adminMail:adminEmailList){
                mailer.sendMail(adminMail,"New BIOASTER OPENLdap account has been created", adminMsg);
            }
             result=true;
        }catch (MessagingException e){

            throw new MessagingException(e);
        }
        return result;

    }

    def boolean sendImportSuccessAlertEmail(List<String> adminEmailList,String userName) {
        def result=false;
            String msg= mailMessage.getImportSuccessMessage(userName);
        try {
            for (String adminMail : adminEmailList) {
                mailer.sendMail(adminMail, " New user registration request waiting for Validation", msg);
            }
            result=true;
        }catch (MessagingException e){
            throw new MessagingException(e);
        }
        return result;
    }
}
