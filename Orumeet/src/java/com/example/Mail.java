/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author gahha
 */
package com.example;
 
import com.example.SmtpAuthenticator;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import oru.inf.InfDB;
import oru.inf.InfException;
 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author gahha
 */
    @Stateless
    @LocalBean
public class Mail {
    String to;
    private String from = "david.w91@hotmail.com";
    String message;
    String subject;
    String smtpServ;
    int motesdags = 0;
    private static InfDB idb;
    String intressantinlagg;
    String inbjudan;
    String snartmote;
 
    /**
     *
     */
    public Mail() {
    }
    /**
     * Creates new form Elev
     * @param db
     */
    public Mail(InfDB db) {
        this.idb = db;
    }
 
    /**
     *
     * @param to
     * @param subject
     * @param body
     * @return
     */
    public int sendinbjudan(String to, String subject, String body){
        try
        {
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
              // -- Create a new message --
              MimeMessage message = new MimeMessage(session);
              try {
           
             message.setFrom(new InternetAddress(from));
             InternetAddress[] address = {new InternetAddress(to)};
             message.setRecipients(Message.RecipientType.TO, address);
             message.setSubject(subject);
             message.setText(body);
               
              // -- Set the FROM and TO fields --
              message.setFrom(new InternetAddress(from));
              message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
              message.setSubject(subject);
              message.setText (inbjudan);
             
              // -- Set some other header information --
              message.setHeader("You got mail", "From David" );
              message.setSentDate(new Date());
              // -- Send the message --
              Transport.send(message);
              System.out.println("Message sent to"+to+" OK." );
              return 0;
              }
              catch (MessagingException ex) {
               System.out.println("Exception"+ex);
           }
        }
        catch (Exception ex)
        {
          ex.printStackTrace();
          System.out.println("Exception "+ex);
          return -1;
        }
        return -1;
    }
   
    /**
     *
     * @param to
     * @param subject
     * @param body
     * @return
     */
    public int sendSnartmote (String to, String subject, String body){
    try
        {
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
              // -- Create a new message --
              MimeMessage message = new MimeMessage(session);
              try {
           
             message.setFrom(new InternetAddress(from));//change accordingly
             InternetAddress[] address = {new InternetAddress(to)};
             message.setRecipients(Message.RecipientType.TO, address);
             message.setSubject(subject);
             message.setText(body);
               
              // -- Set the FROM and TO fields --
              message.setFrom(new InternetAddress(from));
              message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
              message.setSubject(subject);
              String sql = "SELECT EMAIL FROM LARARE WHERE NAMN = '" + getTo() + "'";
              ArrayList<HashMap<String,String>> lista = idb.fetchRows(sql);
              for(HashMap<String,String> minlista:lista){
              String mail = minlista.get("EMAIL");
              message.setText (snartmote);
             
              // -- Set some other header information --
              message.setHeader("You got mail", "From David" );
              message.setSentDate(new Date());
              // -- Send the message --
              Transport.send(message);
              System.out.println("Message sent to"+to+" OK." );
              return 0;
              }
              }
              catch (MessagingException ex) {
               System.out.println("Exception"+ex);
           }
        }
        catch (Exception ex)
        {
          ex.printStackTrace();
          System.out.println("Exception "+ex);
          return -1;
        }
        return -1;
    }

    /**
     *
     * @param to
     * @param subject
     * @param body
     * @return
     */
    public int Sendintressantinlagg (String to, String subject, String body){
    try
        {
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
              // -- Create a new message --
              MimeMessage message = new MimeMessage(session);
              try {
           
             message.setFrom(new InternetAddress(from));//change accordingly
             InternetAddress[] address = {new InternetAddress(to)};
             message.setRecipients(Message.RecipientType.TO, address);
             message.setSubject(subject);
             message.setText(body);
               
              // -- Set the FROM and TO fields --
              message.setFrom(new InternetAddress(from));
              message.setSubject(subject);
              message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
              message.setText (snartmote);
             
              // -- Set some other header information --
              message.setHeader("You got mail", "From David" );
              message.setSentDate(new Date());
              // -- Send the message --
              Transport.send(message);
              System.out.println("Message sent to"+to+" OK." );
              return 0;
              }
              catch (MessagingException ex) {
               System.out.println("Exception"+ex);
           }
        }
        catch (Exception ex)
        {
          ex.printStackTrace();
          System.out.println("Exception "+ex);
          return -1;
        }
        return -1;
    }
   
   
 
 
   
 
// Also include an inner class that is used for authentication purposes
     /**
     * @return the to
     */
    public String getTo() {
        return to;
    }
 
    /**
     * @param to the to to set
     */
    public void setTo(String to) {
        this.to = to;
    }
 
    /**
     * @return the from
     */
    public String getFrom() {
        return from;
    }
 
    /**
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.from = from;
    }
 
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
 
    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
 
    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }
 
    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }
 
    /**
     * @return the smtpServ
     */
    public String getSmtpServ() {
        return smtpServ;
    }
 
    /**
     * @param smtpServ the smtpServ to set
     */
    public void setSmtpServ(String smtpServ) {
        this.smtpServ = smtpServ;
    }

    /**
     *
     * @param snartmote
     */
    public void snartmote(String snartmote) {
        try{
        String sql = "SELECT PLATS FROM MOTE JOIN LARARE_MOTE ON MOTE.ID = LARARE_MOTE.MOTE JOIN LARARE ON LARARE_MOTE.LARARE = LARARE.ID WHERE LARARE.NAMN = '" + getTo() + "'";
              ArrayList<HashMap<String,String>> lista = idb.fetchRows(sql);
              for(HashMap<String,String> minlista:lista){
              String plats = minlista.get("PLATS");
        snartmote = "Hej" + "\n" +  "Det kommer snart att vara ett möte som du ska vara delaktig i. Mötet är i sal: " + plats +"";
              }
              }
       
               catch(InfException e){
        System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param inbjudan
     */
    public void inbjudan(String inbjudan){
        String namn = "sa"; //mote.getNamn
        ArrayList<String> datum = new ArrayList<>();
        try{
        String sql = "SELECT DATUM FROM MOTE WHERE NAMN = '" + namn + "'";
              ArrayList<HashMap<String,String>> lista = idb.fetchRows(sql);
              for(HashMap<String,String> minlista:lista){
              datum.add(minlista.get("DATUM"));}
              inbjudan = "Du har blivit inbjuden till ett möte. Logga in för att välja vilka av dessa datum som passar dig:'" + datum + "'Eller neka inbjudan.";
        }//Detta kan behövas fixa med lite. while
        catch(InfException e){
        System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param intressantinlägg
     */
    public void intressantinlagg (String intressantinlägg){
        intressantinlagg = "Det finns ett inlägg som kan vara intressant för dig.";
    }
}