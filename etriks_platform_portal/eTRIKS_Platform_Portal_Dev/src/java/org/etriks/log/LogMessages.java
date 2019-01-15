package org.etriks.log;

/**
 * Created by pliu on 4/11/14.
 */
public class LogMessages {

    public static String getRequestSubmissionMessage(String ip,String firstName,String lastName){

        return ip+" submitted account creation request with first name: "+firstName+" and last name: "+lastName+" with success";}

    public static String getAgreePrivacyMessage(String ip){

        return ip+" agree to the privacy protection term";
    }
    public static String getChangePwdMessage(String uid){
        return uid+" has changed his password";
    }

    public static String getRequestSubmissionFailMessage(String clientIp,String firstName,String lastName){
       return clientIp+ " try to creat account with first name: "+
                firstName+" and last name: "+lastName+" failed the attribute validations";
    }
    public static String getRequestViewedMessage(String validator,String requester){

        return validator+" viewed the registration request information of "+requester;
    }

    public static String getRequestValidatedMessage(String validator,String requester){
        return validator+" validated the registration request of "+requester;
    }

    public static String getRequestDeletedMessage(String validator,String requesterFirstName,String requesterLastName,String requesterEmail){
        return validator+" deleted the registration request of firstName: "+requesterFirstName+", lastName:"+requesterLastName+", mail: "+requesterEmail;
    }

    public static String getPublicMemberCreationMessage(String clientIp,String firstName,String lastName){
        return clientIp+ " create account with first name: "+
                firstName+" and last name: "+lastName+" in the public community ldap";
    }
    public static String getDataPrivacyTrainingAgreementMessage(String validator,String firstName,String lastName){
        return validator+" has confirmed that he provided privacy protection training information to "+firstName+" "+lastName;
    }
}
