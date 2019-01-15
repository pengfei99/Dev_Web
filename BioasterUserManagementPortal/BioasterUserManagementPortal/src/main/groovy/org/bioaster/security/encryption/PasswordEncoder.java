package org.bioaster.security.encryption;

/**
 * Created by pliu on 2/14/17.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordEncoder {


    /* Get actual class name to be printed on */
    static Logger log = LoggerFactory.getLogger(PasswordEncoder.class);
    private MessageDigest mDigest = null;
    private final String Defaultalg="SHA";


    public PasswordEncoder() {
        try {
            mDigest = MessageDigest.getInstance(Defaultalg);
        } catch (NoSuchAlgorithmException e) {
            log.error("Password encoder can't find your encryption algorithm "+e.getMessage());
        }
    }



    @SuppressWarnings("unused")
    public PasswordEncoder(String alg) throws NoSuchAlgorithmException{
        mDigest = MessageDigest.getInstance(alg);
    }


    public String encPasswordMd5(String password) throws IOException {
        String label="{MD5}";
        mDigest.reset();
        mDigest.update(password.getBytes());
        byte[] pwhash=mDigest.digest();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        outputStream.write(pwhash);
        byte source[] = outputStream.toByteArray( );


        String encString=Base64.getEncoder().encodeToString(source);

        return label + encString;
    }

    public String encPassword(String password) throws IOException{
        byte[] salt = this.randSalt();
        return createDigest(salt, password);

    }

    private String createDigest(byte[] salt,String pwd) throws IOException{
        String label="{SSHA}";

        // Update digest object with byte array of the source clear text
        // string and the salt
        mDigest.reset();
        mDigest.update(pwd.getBytes());
        mDigest.update(salt);

        // Complete hash computation, this results in binary data
        byte[] pwhash = mDigest.digest();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        outputStream.write(pwhash);
        outputStream.write(salt);

        byte source[] = outputStream.toByteArray( );


        String encString=Base64.getEncoder().encodeToString(source);

        return label + encString;
    }

    private byte[] randSalt(){
        int saltLen = 8;
        byte[] b = new byte[saltLen];
        for(int i = 0;i < 8;i++){

            byte bt = (byte)(((Math.random())*256)-128);
            //System.out.println(bt);
            b[i]=bt;
        }
        return b;
    }

}
