package org.etriks.security.encryption;

/**
 * Created by pliu on 4/17/14.
 */
public class PasswordStrength {

    private int checkPassword(String password) {
        int strengthPercentage=0;
        String[] partialRegexChecks = { ".*[a-z]+.*", // lower
                ".*[A-Z]+.*", // upper
                ".*[\\d]+.*", // digits
                ".*[@#$%]+.*" // symbols
        };


        if (password.matches(partialRegexChecks[0])) {
            strengthPercentage+=1;
        }
        if (password.matches(partialRegexChecks[1])) {
            strengthPercentage+=1;
        }
        if (password.matches(partialRegexChecks[2])) {
            strengthPercentage+=1;
        }
        if (password.matches(partialRegexChecks[3])) {
            strengthPercentage+=1;
        }
       if (password.length()>8){
           strengthPercentage+=1;
       }

        return strengthPercentage;
    }
/*This method return a String representation of the password*/
    public String checkPasswordStrength (String password){
         String pwdlevel;

        int strength=this.checkPassword(password);

        switch (strength){
            case 1: pwdlevel="Extremely weak"; break;
            case 2: pwdlevel="Weak"; break;
            case 3: pwdlevel="Medium"; break;
            case 4: pwdlevel="Strong"; break;
            case 5: pwdlevel="Very strong"; break;
            default : pwdlevel="Unknown";
        }
       // System.out.println(pwdlevel);
        return pwdlevel;
    }

    public int checkPasswordStrengthInInt(String password){
        return this.checkPassword(password);
    }

}
