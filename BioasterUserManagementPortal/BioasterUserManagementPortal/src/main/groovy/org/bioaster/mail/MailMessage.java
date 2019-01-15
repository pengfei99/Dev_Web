package org.bioaster.mail;

import org.apache.commons.lang.text.StrSubstitutor;
import org.bioaster.security.config.BUMPConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pliu on 2/27/17.
 */
public class MailMessage {

    private static MailMessage mailInstance=new MailMessage();
    private MailMessage(){}
    public static MailMessage getInstance(){return mailInstance;}
    private static String templatePath=BUMPConfig.mailTemplatePath;
    static Logger log = LoggerFactory.getLogger(MailMessage.class);




    //This method returns a confirmation message for user registration

    public String getAccountActivationMessage(String login){

        Map<String, String> valuesMap = new HashMap<String, String>();
        valuesMap.put("login", login);
        valuesMap.put("supportMail", BUMPConfig.supportMail);
        return getMessageFromTemplate("accountActivation.stemp", valuesMap);
    }


    //This method returns a new registration request alert to the choosen validateur

    public String getRegistrationRequestAlertMessage(String userName){
        Map<String, String> valuesMap = new HashMap<String, String>();
        valuesMap.put("userName", userName);
        valuesMap.put("supportMail", BUMPConfig.supportMail);
        return getMessageFromTemplate("registrationRequestAlert.stemp",valuesMap);
    }

    //This method returns a new account creation confirmation message to admin user
    public String getAccountCreationConfirmationMessage(String  userName,String validator){
        Map<String, String> valuesMap = new HashMap<String, String>();
        valuesMap.put("uid", userName);
        valuesMap.put("validator",validator);
        valuesMap.put("supportMail", BUMPConfig.supportMail);
        return getMessageFromTemplate("accountCreationConfirmationMessage.stemp",valuesMap);

    }


    //This method returns a confirmation which user account creation request has been deleted by admin user
    public String getRegistrationRequestDeletionMessage(String projectName,String validator){
        Map<String, String> valuesMap = new HashMap<String, String>();
        valuesMap.put("projectName", projectName);
        valuesMap.put("validator",validator);
        valuesMap.put("supportMail", BUMPConfig.supportMail);
        return getMessageFromTemplate("registrationRequestDeletionMessage.stemp",valuesMap);
    }

    //This method returns a new account creation confirmation message to admin user
    public String getRequestValidatedByValidatorMessage(String validator,String requestedUserName){
        Map<String, String> valuesMap = new HashMap<String, String>();
        valuesMap.put("requesterName", requestedUserName);
        valuesMap.put("validator",validator);
        valuesMap.put("supportMail", BUMPConfig.supportMail);
        return getMessageFromTemplate("requestValidatedByValidatorMessage.stemp",valuesMap);

    }

    //This method returns a new account creation confirmation message to admin user
    public String getResetPasswordMessage(String projectName,String uid,String password){
        Map<String, String> valuesMap = new HashMap<String, String>();
        valuesMap.put("projectName", projectName);
        valuesMap.put("uid",uid);
        valuesMap.put("password",password);
        valuesMap.put("supportMail", BUMPConfig.supportMail);
        return getMessageFromTemplate("resetPasswordMessage.stemp",valuesMap);
    }

    public String getImportSuccessMessage(String userName) {
        Map<String, String> valuesMap = new HashMap<String, String>();
        valuesMap.put("userName", userName);
        valuesMap.put("supportMail", BUMPConfig.supportMail);
        return getMessageFromTemplate("importSuccessMessage.stemp",valuesMap);
    }


   public String getMessageFromTemplate(String fileName, Map<String,String> valuesMap){
       String filePath=templatePath+fileName;
       String content = "";
       try
       {
           content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
       }
       catch (IOException e)
       {
           log.error("Email template file is missing. Java exception: "+e.toString());
       }

       StrSubstitutor sub = new StrSubstitutor(valuesMap);
       return sub.replace(content);
   }
    
}