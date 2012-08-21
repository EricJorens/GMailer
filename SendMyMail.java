

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
*
* @author Eric Jorens
*/
public class SendMyMail {
    private String reciever;
    private String subject;;
    private String messageText;
    private String userName;
    private String password;
    
    private Properties props = new Properties();
    
    public SendMyMail(){
        
    }
    
    public SendMyMail(String r, String s, String m){
        reciever = r;
        subject = s;
        messageText = m;
    }
    
    public void setSmtp(String smtp, String port, String auth){
        props.put("mail.smtp.host", smtp);
        props.put("mail.smtp.socketFactory.port", port);
	props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
	props.put("mail.smtp.auth", auth);
	props.put("mail.smtp.port", port);  
    }
    public void setReciever(String s){
        reciever = s;
    }
    public void setSubject(String s){
        subject = s;
    }
    public void setMessageText(String s){
        messageText = s;
    }
    public void setUserName(String s){
        userName = s;
    }
    public void setPassword(String s){
        password = s;
    }
    public String getUserName(){
        return userName;
    }
    public String getPassword(){
        return password;
    }
    public String getReciever(){
        return reciever;
    }
    public String getSubject(){
        return subject;
    }
    public String getMessageText(){
        return messageText;
    }
    
    
    public int SendIt(){
        /*
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.socketFactory.port", "465");
	props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.port", "465");
 */
	Session session = Session.getDefaultInstance(props,
		new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		});
 
	try {

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("from@no-spam.com"));
                        
		message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(reciever));
                        
		message.setSubject(subject);
                        
		message.setText(messageText);
 
		Transport.send(message);
 
		System.out.println("Done");
                
                return 0;
 
	} catch (MessagingException e) {
                return 1;
		//throw new RuntimeException(e);
                
	}
        
    }
    
    
    public static void main(String[] args) {
		
    }
             
}
