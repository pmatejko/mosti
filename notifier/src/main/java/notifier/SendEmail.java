package notifier;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;



public class SendEmail {
	
	public static void main(String[] args) {
		SendEmail.send("aa", "aa");
	}
	
	public static void send(String title, String content) {    
		System.out.println("im here");
		try {
			GoogleMail.Send("smalcerztest", "plemionaa", "smalcerzszymonn@gmail.com", "od szymonga", "kasia jest supix");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
}