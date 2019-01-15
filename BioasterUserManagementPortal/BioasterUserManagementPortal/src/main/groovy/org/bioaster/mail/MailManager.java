package org.bioaster.mail;

import org.bioaster.security.config.BioasterMailConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.MessagingException;

/**
 * Created by pliu on 2/27/17.
 */
public class MailManager {

    static Logger log = LoggerFactory.getLogger(MailManager.class);

    private static MailManager mailManagerInstance=new MailManager();

    private MailManager(){}

    public static MailManager getInstance(){
        return mailManagerInstance;
    }

    BioasterMailConfig mailConfig=BioasterMailConfig.getInstance();

    private String personalSenderName="admin@bioaster.org";

    public void sendMail(String receiverAddr,String mailSubject,String mailBody) throws MessagingException{


        //set the session properties to connect to the mail host server.
        Properties props=new Properties();
        props.put("mail.smtp.host", mailConfig.SMTP_HOST_NAME);
        props.put("mail.smtp.auth", "false");
        // props.put("mail.debug", "true");
        props.put("mail.smtp.port", mailConfig.SMTP_PORT);
        props.put("mail.smtp.socketFactory.port", mailConfig.SMTP_PORT);
        props.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getInstance(props);

        //Activate the debug mode
        //session.setDebug(true);

        MimeMessage msg = new MimeMessage(session);



        //set the sender email address
        InternetAddress addressFrom = null;
        try {
            addressFrom = new InternetAddress(mailConfig.SenderMailAddr,personalSenderName);

        } catch (UnsupportedEncodingException e) {
            log.error("In JAutoMail, cannot create sender address. Java exception: "+e.toString());
        }
        msg.setFrom(addressFrom);

        //set the receiver email address
        InternetAddress addressTo = new InternetAddress(receiverAddr);
        msg.setRecipient(Message.RecipientType.TO, addressTo);

        // Setting the Subject and Content Type
        msg.setSubject(mailSubject);
        msg.setContent(mailBody, "text/plain");
        Transport.send(msg);

    }
}


