package notifier.senders;



import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.AddressException;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import notifier.exceptions.BadLengthTelephoneNumberException;

public class MailSenderSendTest {

	private Sender sender;
	
	
	
	
	@Test(expected=MessagingException.class)
	public void testMailSender_BadSenderConfiguration() throws AddressException, MessagingException, BadLengthTelephoneNumberException {
		
		try {
			this.sender = new MailSender("configGmail2.json");
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.sender.send("asd", "asd", "asd");
	}
	
	@Test(expected=AddressException.class)
	public void testMailSender_BadFormatOfReceiverEmail() throws AddressException, MessagingException, BadLengthTelephoneNumberException {
		
		try {
			this.sender = new MailSender("configGmail.json");
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.sender.send("a s !;;d ", "asd", "asd");
	}
	
	
	@Test(expected=SendFailedException.class)
	public void testMailSender_NonExistingReceiverEmail() throws AddressException, MessagingException, BadLengthTelephoneNumberException {
		
		try {
			this.sender = new MailSender("configGmail.json");
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.sender.send("asd", "asd", "asd");
	}
	
	@Test(expected=BadLengthTelephoneNumberException.class)
	public void testSmsSender_TooShortNumber() throws BadLengthTelephoneNumberException, AddressException, MessagingException{
		
		try {
			this.sender = new SmsSender("configVianettSms.json");
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.sender.send("9", "asd", "asd");
	}
	
	
	@Test(expected=BadLengthTelephoneNumberException.class)
	public void testSmsSender_TooLongNumber() throws BadLengthTelephoneNumberException, AddressException, MessagingException{
		
		try {
			this.sender = new SmsSender("configVianettSms.json");
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.sender.send("12312312356756756", "asd", "asd");
	}

}
