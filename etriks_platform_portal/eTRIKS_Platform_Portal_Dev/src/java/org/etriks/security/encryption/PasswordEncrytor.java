package org.etriks.security.encryption;

/**
 * Created by pliu on 3/28/14.
 */
import org.apache.commons.codec.binary.Base64;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncrytor {

/*
* @params String algorithm
* @params String password
* @return String password digested with the header {MD5} or {SHA}
* This method return the digest of the password in base64*/

    public String digestBase64(String algorithm,String password) throws NoSuchAlgorithmException {
        //Create the instance of the digest with the given algorithm.
        MessageDigest md = MessageDigest.getInstance(algorithm);
        //Put the password in the digest.
        md.update(password.getBytes());
        //get the output of the digest of the password.
        byte[] hashHex = md.digest();
        //Convert the output in to base64
         //The following base64 encoder may causer errors in some older version java enviroment.So I replace it with the current method
        //String encodedHexB64 = Base64.encodeBase64String(hashHex);
String encodedHexB64=new String(Base64.encodeBase64(hashHex));
        return "{"+ algorithm+"}"+ encodedHexB64;

    }


    /*
* @params String algorithm
* @params String password
* @return String password digested with the header {MD5} or {SHA}
* This method return the digest of the password in base64*/
    public String digestHex(String algorithm, String password) throws NoSuchAlgorithmException {
        MessageDigest md=MessageDigest.getInstance(algorithm);
        md.update(password.getBytes());
        String digHex = new BigInteger(1,md.digest()).toString(16);

        return "{"+ algorithm+"}"+digHex;
    }

}