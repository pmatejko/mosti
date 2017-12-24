package notifier.senders;



import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.AddressException;

import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import exceptions.BadLengthTelephoneNumberException;
import exceptions.SenderException;
import model.News;
import model.User;
import model.UserNewsDTO;
import notifier.message.UglyMessageGenerator;

public class MailSenderSendTest {

	private Sender sender;
	private User user = new User();
	private List<News> newsList = new LinkedList<>();
	private News ourNews = new News();
	
	@Before
	public void config() {
		user.setEmail("smalcerzszymonn@gmail.com");
		ourNews.setContent("content");
		newsList.add(ourNews);
	}
	
	
	@Test(expected=MessagingException.class)
	public void testMailSender_BadSenderConfiguration() throws Throwable{
		
		try {
			this.sender = new MailSender("configGmail2.json");
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			//bad email of sender
			user.setEmail("asd");
			this.sender.send(new UglyMessageGenerator(new UserNewsDTO(user, newsList)));
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
			//bad email of receiver
			user.setEmail("a s !;;d ");
			this.sender.send(new UglyMessageGenerator(new UserNewsDTO(user, newsList)));
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
			//non existing email of receiver
			user.setEmail("asd");
			this.sender.send(new UglyMessageGenerator(new UserNewsDTO(user, newsList)));
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
			//bad telephone Number Of user - too short
			user.setEmail("9");
			this.sender.send(new UglyMessageGenerator(new UserNewsDTO(user, newsList)));
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
			//bad telephone Number Of user - too long
			user.setEmail("123123123123123123");
			this.sender.send(new UglyMessageGenerator(new UserNewsDTO(user, newsList)));
		} catch (SenderException e) {
			throw e.getCause();
		}
	}

}
