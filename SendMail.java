import java.util.Properties;    
import javax.mail.*;    
import javax.mail.internet.*;    
class SendMail{  
    public static void send(String to,String sub,String pass,String name){  
          System.out.println("coming in mail"+to+"sub "+sub+"msg "+pass);
          Properties props = new Properties();    
          props.put("mail.smtp.host", "smtp.gmail.com");    
          props.put("mail.smtp.socketFactory.port", "465");    
          props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");    
          props.put("mail.smtp.auth", "true");    
          props.put("mail.smtp.port", "465");    
          //get Session   
          Session session = Session.getDefaultInstance(props,    
          new javax.mail.Authenticator() {    
           protected PasswordAuthentication getPasswordAuthentication() {    
           return new PasswordAuthentication("anchalaroraproject2000@gmail.com","anchal123");  
           }    
          });    
          //compose message    
          try {    
	           MimeMessage message = new MimeMessage(session);    
	           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
	           message.setSubject(sub);    
	           //message.setText("You have Added as a Librarian. Please Use below Username and Password to Login.");  
	           message.setContent("<html>\r\n"
	           		+ "<body>\r\n"
	           		+ "Hello "+name+", <br><br> You have Added as a Librarian. Please Use below Username and Password to Login.<br><br><br> Username: "+to+"<br> Password: "+pass+"<br><br><br><br>\r\n"
	           		+ "Thanks,<br>Admin Team\r\n"
	           		+ "</body>\r\n"
	           		+ "</html>", "text/html");
	           //send message  
	           Transport.send(message);    
	           System.out.println("message sent successfully");    
          } 
          catch (MessagingException e) {
        	  throw new RuntimeException(e);
          }    
             
    }  
}  
 