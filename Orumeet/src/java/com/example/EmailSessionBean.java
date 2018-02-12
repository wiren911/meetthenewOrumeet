package com.example;
import java.util.Properties;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
 * @author David
 */
@Stateless
@LocalBean
public class EmailSessionBean {
    


    private String from = "david.w91@hotmail.com";
    
    /**
     *
     * @param to
     * @param subject
     * @param body
     */

    public void sendEmail(String to, String subject, String body){
        
        //adding properties to establish a connection to mail server in this case hotmail.
        Properties props = new Properties();        
        props.put("mail.smtp.host", "smtp.live.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.live.com");

        
        SmtpAuthenticator auth = new SmtpAuthenticator();
        Session session = Session.getInstance(props, auth);
        session.setDebug(true);
        
        //fetch input from the fields to produce the mime message.
        MimeMessage message = new MimeMessage(session);
        try {
            
            message.setFrom(new InternetAddress(from));//change accordingly
            InternetAddress[] address = {new InternetAddress(to)};
            message.setRecipients(Message.RecipientType.TO, address);
            message.setSubject(subject);
            message.setText(body);
            
            //send message using the Transport() funktion            
            Transport.send(message);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("message sent successfully!!"));
//            System.out.println("message sent successfully");

        } 
           catch (MessagingException ex) {
               System.out.println("Exception"+ex);
           }
    }
}
