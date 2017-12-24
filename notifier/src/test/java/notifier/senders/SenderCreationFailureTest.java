package notifier.senders;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
@RunWith(Parameterized.class)
public class SenderCreationFailureTest {

	
	

	private String expectedHost;
	private String expectedNick;
	private String expectedPort;
	private Sender sender;
	
	public SenderCreationFailureTest(Sender sender, String expectedHost, String expectedNick, String expectedPort) throws IOException {
		this.expectedHost = expectedHost;
		this.expectedNick = expectedNick;
		this.expectedPort = expectedPort;
		this.sender = sender;
	}

	@Parameters
	public static Collection<Object[]> testConditions() throws IOException, ParseException {
		Object expectedOutputs[][] = { 
				{new MailSender("configGmail.json"), "gmailFAIL.com", "smalcerztest3", "666" }, 
				{new MailSender("configGmail2.json"), "asdFAIL.com", "asddd", "666" },
				{new SmsSender("configVianettSms.json"), "cpa.vianett.no", "smalcerzszymonn@gmail.com", "31337" }};
		return Arrays.asList(expectedOutputs);
	}

	@Test
	public void testIfTwoHostsAreNotEqual() {
		assertNotSame((this.sender).getHost(), 
				expectedHost);
	}
	
	@Test
	public void testIfTwoPortsAreNotEqual() {
		assertNotSame(this.sender.getPort(), 
				expectedPort);
	}
	
	
	@Test
	public void testIfTwoNicksAreNotEqual() {
		assertNotSame(this.sender.getNick(), 
				expectedNick);
	}
}
