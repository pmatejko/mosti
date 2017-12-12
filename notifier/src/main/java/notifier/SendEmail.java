package notifier;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;



public class SendEmail {
	
	public static void send(String title, String content, String mail) {  
		try {
			GoogleMail.Send("smalcerztest", "plemionaa", mail, title, content);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
}