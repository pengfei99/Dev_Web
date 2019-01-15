package org.etriks.mail;

/**
 * Created by pliu on 3/28/14.
 */

public final class AutoMailConfig {
    private static final String SMTP_HOST_NAME="smtp.gmail.com";
    private static final String SMTP_PORT = "465";
    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    private static final String SenderMailAddr="etriksmail@gmail.com";
   // private static final String SenderMailAddr="pliu@cc.in2p3.fr";
    private static final String SenderMailPassword="Admin@etriks";







    public static String getSmtpHostName() {
        return SMTP_HOST_NAME;
    }



    public static String getSmtpPort() {
        return SMTP_PORT;
    }



    public static String getSslFactory() {
        return SSL_FACTORY;
    }



    public static String getSendermailaddr() {
        return SenderMailAddr;
    }



    public static String getSendermailpassword() {
        return SenderMailPassword;
    }



}