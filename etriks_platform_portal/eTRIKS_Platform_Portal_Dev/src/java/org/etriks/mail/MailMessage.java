package org.etriks.mail;

/**
 * Created by pliu on 3/28/14.
 */
public class MailMessage {

    private String eol=System.getProperty("line.separator");

    private String nextLine="\r\n";

    private String head="**************Please do not reply this email****************"+eol+nextLine + "This email is sent automatically by eTRIKS platform. "+eol;

    private String tail="Please contact the admin@etriks.org, if you have any problems!";



    //This method returns a confirmation message for user registration

    public String getAccountActivationMessage(String login){

        String body="Your account has been activated by the eTRIKS platform."+ eol +nextLine+
                "******************************"+eol+
                "Your login is : "+ login + eol +
                "Your password is the password which you entered when create your account"+eol
                +"*****************************"+eol+nextLine;



        String message=head+body+tail;

        return message;
    }


    //This method returns a new registration request alert to the choosen validateur

    public String getRegistrationRequestAlertMessage(String userName){

        String body= nextLine+"Dear CZAR validator, There is a new user registration request."+ eol +nextLine+
                "Please check on the user information and their groups before any validation"+eol+nextLine+
                "*********************************************"+eol+
                "Username is : "+ userName + eol +
                "*********************************************"+eol+nextLine+

                "Please go to the admin dashboard (https://portal.etriks.org/Portal/dashBoard/index) to validate or delete the request"+eol+nextLine;



        String message=head+body+tail;

        return message;
    }

    //This method returns a new account creation confirmation message to admin user
    public String getAccountCreationConfirmationMessage(String  userName,String validator){
        String body="A new user account has been created"+ eol+nextLine+
                "*********************************************"+eol+nextLine+
                "The user uid is : "+userName+eol+

                "The validator is : "+validator+eol+nextLine+

                "*********************************************"+eol+nextLine+
                "Please go to the admin dashboard (https://portal.etriks.org/Portal/dashBoard/index) to check user information"+eol+nextLine;

        String message=head+body+tail;
        return message;
    }


    //This method returns a new account creation confirmation message to admin user
    public String getRequestDeletionMessage(String projectName,String validator){
        String body="Your request to create an account in "+projectName +" has been deleted"+eol+nextLine+
                "*********************************************"+eol+nextLine+

                "The validator who delete your request is : "+validator+eol+nextLine+

                "*********************************************"+eol+nextLine+
                "Please contact your project manager, if you think you have the right to have an account"+eol+nextLine;

        String message=head+body+tail;
        return message;
    }

    //This method returns a new account creation confirmation message to admin user
    public String getRequestValidatedByCZARMessage(String projectName,String validator,String requestedUserName){
        String body="There is a user registration request in "+projectName +" has been validated"+eol+nextLine+
                "*********************************************"+eol+nextLine+

                "The user name of the request is : "+requestedUserName+eol+nextLine+
                "The project validator who validate the request is : "+validator+eol+nextLine+

                "*********************************************"+eol+nextLine+
                "Please contact your project manager, if you think this user should not be validated"+eol+nextLine;

        String message=head+body+tail;
        return message;
    }

    //This method returns a new account creation confirmation message to admin user
    public String getResetPasswordMessage(String projectName,String uid,String password){
        String body="You have requested to reset your password in "+projectName +" etriks platform. Your old password will be replaced by the auto generated password "+eol+nextLine+
                "*********************************************"+eol+nextLine+
                "Your login is : "+uid+eol+nextLine+
                "Your temporary password is : "+password+eol+nextLine+
                "You need to go to etriks portal (https://portal.etriks.org/Portal/) to change your password as soon as possible : "+eol+nextLine+

                "*********************************************"+eol+nextLine+
                "Please contact your project manager immediately, if it's not you who requested to reset your password"+eol+nextLine;

        String message=head+body+tail;
        return message;
    }

}