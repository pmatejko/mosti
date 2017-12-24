package notifier.senders;



import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.AddressException;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import exceptions.BadLengthTelephoneNumberException;
import exceptions.SenderException;

public class MailSenderSendTest {

	private Sender sender;
	
	
	
	
	@Test(expected=MessagingException.class)
	public void testMailSender_BadSenderConfiguration() throws Throwable{
		
		try {
			this.sender = new MailSender("configGmail2.json");
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			this.sender.send("asd", "asd", "asd");
		} catch (SenderException e) {
			throw e.getCause();
		}
	}
	
	@Test(expected=AddressException.class)
	public void testMailSender_BadFormatOfReceiverEmail() throws Throwable{
		
		try {
			this.sender = new MailSender("configGmail.json");
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			this.sender.send("a s !;;d ", "asd", "asd");
		} catch (SenderException e) {
			throw e.getCause();
		}
	}
	
	
	@Test(expected=SendFailedException.class)
	public void testMailSender_NonExistingReceiverEmail() throws Throwable {
		
		try {
			this.sender = new MailSender("configGmail.json");
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			this.sender.send("asd", "asd", "asd");
		} catch (SenderException e) {
			// TODO Auto-generated catch block
			throw e.getCause();
		}
	}
	
	@Test(expected=BadLengthTelephoneNumberException.class)
	public void testSmsSender_TooShortNumber() throws Throwable{
		
		try {
			this.sender = new SmsSender("configVianettSms.json");
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			this.sender.send("9", "asd", "asd");
		} catch (SenderException e) {
			throw e.getCause();
		}
		
	}
	
	
	@Test(expected=BadLengthTelephoneNumberException.class)
	public void testSmsSender_TooLongNumber() throws Throwable{
		
		try {
			this.sender = new SmsSender("configVianettSms.json");
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			this.sender.send("12312312356756756", "asd", "asd");
		} catch (SenderException e) {
			throw e.getCause();
		}
	}

}
