package org.etriks.mail;

/**
 * Created by pliu on 7/11/14.
 */
public class CCAutoMailConfig {

  // private static final String SMTP_HOST_NAME="127.0.0.1";
 // private static final String SMTP_HOST_NAME="ccmailrelay.in2p3.fr";
    private static final String SMTP_HOST_NAME="smtp.in2p3.fr";
    private static final String SMTP_PORT = "25";
 //   private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
 //   private static final String SenderMailAddr="etriksmail@gmail.com";
     private static final String SenderMailAddr="admin@etriks.org";








    public static String getSmtpHostName() {
        return SMTP_HOST_NAME;
    }



    public static String getSmtpPort() {
        return SMTP_PORT;
    }



   /* public static String getSslFactory() {
        return SSL_FACTORY;
    }
*/


    public static String getSendermailaddr() {
        return SenderMailAddr;
    }


}