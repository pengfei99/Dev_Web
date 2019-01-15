package org.bioaster.security.encryption;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by pliu on 3/14/17.
 */
public class RandomPwdGenerator {

    private static RandomPwdGenerator pwdGeneratorInstance=new RandomPwdGenerator();
    private SecureRandom random;

    private RandomPwdGenerator(){random=new SecureRandom();}

    public static RandomPwdGenerator getInstance(){
        return pwdGeneratorInstance;
    }

    public String generateRandomPassword(){
        return new BigInteger(60,random).toString(30);
    }

}

