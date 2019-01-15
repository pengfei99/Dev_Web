package org.etriks.security.X509.certificateManagement;


import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/**
 * Created by pliu on 3/20/15.
 */
public class RSAKeyPair {

    private KeyPair paire;

    public void generate(String keyType,int keyLength){
        KeyPairGenerator keyGen = null;
        try {
            keyGen =  KeyPairGenerator.getInstance(keyType);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyGen.initialize(keyLength);
        paire=keyGen.genKeyPair();
        byte[] publicKey = paire.getPublic().getEncoded();
        StringBuffer pubString = new StringBuffer();
        for (int i = 0; i < publicKey.length; ++i) {
            pubString.append(Integer.toHexString(0x0100 + (publicKey[i] & 0x00FF)).substring(1));

            }
        byte[] privateKey=paire.getPrivate().getEncoded();
            StringBuffer priString = new StringBuffer();
            for (int i = 0; i < privateKey.length; ++i) {
                priString.append(Integer.toHexString(0x0100 + (privateKey[i] & 0x00FF)).substring(1));
        }
        System.out.println("Public key:"+pubString);
        System.out.println("Private key:"+priString);

    }
}
