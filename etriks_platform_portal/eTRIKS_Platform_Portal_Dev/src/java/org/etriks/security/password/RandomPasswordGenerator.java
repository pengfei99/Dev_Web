package org.etriks.security.password;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by pliu on 10/21/14.
 */
public class RandomPasswordGenerator {
    private SecureRandom random=new SecureRandom();

    public String generateRandomPassword(){
        return new BigInteger(60,random).toString(30);
    }
}
