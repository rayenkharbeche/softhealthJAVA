/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.pifinal;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author rayen
 */
public class SendMail {
    
     public static void send(String recepient,int mot) throws MessagingException 
    {
        System.out.println("Preparing Send email");
        Properties props = new Properties();
  String host = "smtp.gmail.com";
       props.put("mail.smtp.starttls.enable", "true");
                    props.put("mail.smtp.host", host);
                    props.put("mail.smtp.port", "587");
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.starttls.required", "true");
         props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
       
         
         String myAccountEmail ="rihem.sahbeni30@gmail.com";
        String password ="rihem1234";
        Session session = Session.getDefaultInstance(props,new Authenticator() 
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() 
            {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
            Message message = prepareMessage(session,myAccountEmail,recepient,mot);
            Transport.send(message);
            System.out.println("message sent");
    }

    private static Message prepareMessage(Session session,String myAccountEmail,String recepient,int mot) throws AddressException, MessagingException {
       
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Mot de passe");
            message.setText(mot+" Est Votre code de verification du compte S4S");
            return message;
    }
}
