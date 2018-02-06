package com.example;
import java.util.Properties;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 *
 * @author user
 */
@Stateless
@LocalBean
public class EmailSessionBean {

    private String from = "wirendavid@gmail.com";
    
    /**
     *
     * @param to
     * @param subject
     * @param body
     */

    public void sendEmail(String to, String subject, String body){
                
        Properties props = new Properties();        
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

//        switch(protocol){
//            case SMTPS:
//                props.put("mail.smtp.ssl.enable", true);
//                break;
//            case TLS:
//                props.put("mail.smtp.starttls.enable", true);
//                break;
//        }
          
//        Authenticator authenticator = null;
//        if (auth) {
//            props.put("mail.smtp.auth", true);
//            authenticator = new Authenticator() {
//                private final PasswordAuthentication pa = new PasswordAuthentication(username, password);
//                @Override
//                public PasswordAuthentication getPasswordAuthentication() {
//                    return pa;
//                }
//            };
//        }

//        SmtpAuthenticator authenticator = new SmtpAuthenticator();
//        javax.mail.Message message= new MimeMessage(Session.getDefaultInstance(props, authenticator));
//        Session session = Session.getInstance(props, authenticator);
//        session.setDebug(true);
        
        SmtpAuthenticator auth = new SmtpAuthenticator();
        Session session = Session.getInstance(props, auth);
        session.setDebug(true);
        
        //Session session = Session.getInstance(props, authenticator);
        //session.setDebug(debug);
        
        MimeMessage message = new MimeMessage(session);
        try {
            
            message.setFrom(new InternetAddress(from));//change accordingly
            InternetAddress[] address = {new InternetAddress(to)};
            message.setRecipients(Message.RecipientType.TO, address);
            message.setSubject(subject);
            message.setText(body);
            //send message
            Transport.send(message);

            System.out.println("message sent successfully");

        } 
           catch (MessagingException ex) {
               System.out.println("Exception"+ex);
           }
    }
}
