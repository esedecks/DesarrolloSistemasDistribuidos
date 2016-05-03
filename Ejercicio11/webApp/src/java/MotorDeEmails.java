/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author esedecks
 */
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
public  class MotorDeEmails {
    static String to = "jdecks.visible@outlook.com";
    // Sender's email ID needs to be mentioned
    static String from = "esedecks@gmail.com";
    
    final static String username = "esedecks@gmail.com";
    final static String password = "godofwar5300";
    static Properties props;
    static Session session; 
    private MotorDeEmails(){
    
    }
    public static void setFrom(String from){
        MotorDeEmails.from = from ; 
    }
    public static void setTo(String to){
        MotorDeEmails.to = to ; 
    }
    public static void prepararMotor(){
        props = new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "587");
        session = Session.getInstance(props,
                        new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
			}
                        });
    }
    public static boolean enviarEmailconArchivo(String subject, String mensaje, String uri){
        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(to));
            // Set Subject: header field
            message.setSubject(subject);
            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();
            // Now set the actual message
            messageBodyPart.setText(mensaje);
            // Create a multipar message
            Multipart multipart = new MimeMultipart();
            // Set text message part
            multipart.addBodyPart(messageBodyPart);
            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(uri);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(uri);
            multipart.addBodyPart(messageBodyPart);
            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);

        
            return true; 
        } catch (MessagingException e) {
            e.printStackTrace();
            return false; 
        }
    
    }
    public static void main(String[] args) {
        // Recipient's email ID needs to be mentioned.

	Properties props = new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                        new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
			}
                        });
                
	try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(to));
            // Set Subject: header field
            message.setSubject("Testing");
            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();
            // Now set the actual message
            messageBodyPart.setText("Muchas gracias te enviamos un docmento");
            // Create a multipar message
            Multipart multipart = new MimeMultipart();
            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = "/home/esedecks/Descargas/WIN_20140327_031037.JPG";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);
            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");
        } catch (MessagingException e) {
            e.printStackTrace();
            
        }
    }
}
