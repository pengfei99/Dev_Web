package org.etriks.mail;

/**
 * Created by pliu on 3/28/14.
 */

import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;




public final class JAutoMail {
    //Create an instance of logger
    private final org.apache.log4j.Logger log=Logger.getLogger(getClass());
	/*
	 * Send a single email
	 */

    public void sendMail(String receiverAddr,String mailSubject,String mailBody) throws MessagingException{


        //set the session properties to connect to the mail host server.
        Properties props=new Properties();
        props.put("mail.smtp.host", AutoMailConfig.getSmtpHostName());
        props.put("mail.smtp.auth", "true");
        //props.put("mail.debug", "true");
        props.put("mail.smtp.port", AutoMailConfig.getSmtpPort());
        props.put("mail.smtp.socketFactory.port", AutoMailConfig.getSmtpPort());
        props.put("mail.smtp.socketFactory.class", AutoMailConfig.getSslFactory());
        props.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(AutoMailConfig.getSendermailaddr(), AutoMailConfig.getSendermailpassword());
                    }
                });

        //Activate the debug mode
        //session.setDebug(true);

        MimeMessage msg = new MimeMessage(session);


       // InternetAddress addressFrom=new InternetAddress(from,"admin-etriks@cc.in2p3.fr");

        //set the sender email address
        InternetAddress addressFrom = null;
        try {
            addressFrom = new InternetAddress(AutoMailConfig.getSendermailaddr(),"admin@etriks.org");

        } catch (UnsupportedEncodingException e) {
            log.error("In JAutoMail, cannot create sender address. Java exception: "+e.toString());
        }
        msg.setFrom(addressFrom);

        //set the receiver email address
        InternetAddress addressTo = new InternetAddress();
        addressTo = new InternetAddress(receiverAddr);
        msg.setRecipient(Message.RecipientType.TO, addressTo);

        // Setting the Subject and Content Type
        msg.setSubject(mailSubject);
        msg.setContent(mailBody, "text/plain");
        Transport.send(msg);
    }
}