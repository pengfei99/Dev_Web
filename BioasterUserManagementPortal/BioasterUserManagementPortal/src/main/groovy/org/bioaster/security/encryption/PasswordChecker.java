package org.bioaster.security.encryption;

/**
 * Created by pliu on 3/14/17.
 */
public class PasswordChecker {
    private static PasswordChecker pcheckerInstance=new PasswordChecker();

    private PasswordChecker(){}

    public static PasswordChecker getInstance(){
        return pcheckerInstance;
    }

    private static int checkPassword(String password) {
        int strengthPercentage=0;
        String[] partialRegexChecks = { ".*[a-z]+.*", // lower
                ".*[A-Z]+.*", // upper
                ".*[\\d]+.*", // digits
                ".*[!@#$%^&*+=_-]+.*" // symbols
        };

        for (int i = 0;i < password.length(); i++){
            String currentLetter = Character.toString(password.charAt(i));

            if (currentLetter.matches(partialRegexChecks[0])) {
                strengthPercentage+=3;
            }
            if (currentLetter.matches(partialRegexChecks[1])) {
                strengthPercentage+=4;
            }
            if (currentLetter.matches(partialRegexChecks[2])) {
                strengthPercentage+=2;
            }
            if (currentLetter.matches(partialRegexChecks[3])) {
                strengthPercentage+=5;

            }

        }
        if (password.length()<8){
            strengthPercentage-=10;
        }

        return strengthPercentage;

    }
    /*This method return a String representation of the password*/
    public static String checkPasswordStrength (String password){
        String pwdStrengthLevel;

        int strength=checkPassword(password);
        if (-10<strength && strength<=5){
            pwdStrengthLevel="Extremely_weak";
        }
        else if (5<strength && strength<=15){
            pwdStrengthLevel="Weak";
        }
        else if (15<strength && strength<=25){
            pwdStrengthLevel="Medium";
        }
        else if (25<strength && strength<=35){
            pwdStrengthLevel="Strong";
        }
        else if (35<strength && strength<=55){
            pwdStrengthLevel="Very_strong";
        }
        else if (55<strength){
            pwdStrengthLevel="Over_kill";
        }
        else { pwdStrengthLevel="Unknown";}


        return pwdStrengthLevel;
    }

    public static int checkPasswordStrengthInInt(String password){
        return checkPassword(password);
    }


}