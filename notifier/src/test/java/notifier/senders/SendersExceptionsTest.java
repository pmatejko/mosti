package notifier.senders;



import javax.mail.MessagingException;
import javax.mail.SendFailedException;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.*;


import exceptions.BadLengthTelephoneNumberException;
import exceptions.SenderException;
import notifier.message.UglyMessageGenerator;
import notifier.senders.configuration.Configuration;

public class SendersExceptionsTest {

	
	private static Sender sender;
	private static Configuration conf;
	private static Configuration conf2;
	private static Configuration confSms;
	private static UglyMessageGenerator um;

	
	@BeforeClass
	public static void config() {
		
		sender = mock(Sender.class);
		conf = mock(Configuration.class);
		conf2 = mock(Configuration.class);
		confSms = mock(Configuration.class);
		um = mock(UglyMessageGenerator.class);
		
		
		when(conf.getHost()).thenReturn("gmail.com");
		when(conf.getPort()).thenReturn("465");
		when(conf.getPassword()).thenReturn("plemionaa");
		when(conf.getNick()).thenReturn("smalcerztest");
		
		when(conf2.getHost()).thenReturn("asd.com");
		when(conf2.getPort()).thenReturn("465");
		when(conf2.getPassword()).thenReturn("asd");
		when(conf2.getNick()).thenReturn("smalcerztest2");
		
		when(confSms.getHost()).thenReturn("cpa.vianett.no");
		when(confSms.getPort()).thenReturn("31337");
		when(confSms.getPassword()).thenReturn("c8lmb");
		when(confSms.getNick()).thenReturn("smalcerzszymonn@gmail.com");
		

		
		when(um.getContact()).thenReturn("asd");
		when(um.getEmail()).thenReturn("asd");
		when(um.getTitle()).thenReturn("random title");
		when(um.generateMessageContent()).thenReturn("content");
	}
	
	
	@Test(expected=MessagingException.class)
	public void testMailSender_BadSenderConfiguration() throws Throwable{
		
			
		try {
			//bad email of sender
			sender = new MailSender(conf2);
			when(um.getEmail()).thenReturn("asd");
			sender.send(um);
		} catch (SenderException e) {
			throw e.getCause();
		}
	}
	
	
	@Test(expected=SendFailedException.class)
	public void testMailSender_NonExistingReceiverEmail() throws Throwable {
		
		try {
			//non existing email of receiver
			sender = new MailSender(conf);
			when(um.getEmail()).thenReturn("asd");
			sender.send(um);
			
		} catch (SenderException e) {
			// TODO Auto-generated catch block
			throw e.getCause();
		}
	}
	
	@Test(expected=BadLengthTelephoneNumberException.class)
	public void testSmsSender_TooShortNumber() throws Throwable{

		
		try {
			//bad telephone Number Of user - too short
			sender = new SmsSender(confSms);
			when(um.getEmail()).thenReturn("9");
			sender.send(um);
		} catch (SenderException e) {
			throw e.getCause();
		}
		
	}
	
	
	@Test(expected=BadLengthTelephoneNumberException.class)
	public void testSmsSender_TooLongNumber() throws Throwable{
		
		try {
			//bad telephone Number Of user - too short
			sender = new SmsSender(confSms);
			when(um.getEmail()).thenReturn("98765432123456789");
			sender.send(um);
		} catch (SenderException e) {
			throw e.getCause();
		}
	}

}
