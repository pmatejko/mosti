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

import notifier.senders.configuration.Configuration;
@RunWith(Parameterized.class)
public class SenderCreationTest {

	
	

	private String expectedHost;
	private String expectedNick;
	private String expectedPort;
	private Sender sender;
	
	public SenderCreationTest(Sender sender,String expectedHost, String expectedNick, String expectedPort) throws IOException {
		this.expectedHost = expectedHost;
		this.expectedNick = expectedNick;
		this.expectedPort = expectedPort;
		this.sender = sender;
	}

	@Parameters
	public static Collection<Object[]> testConditions() throws IOException, ParseException {
		Object expectedOutputs[][] = { 
				{new MailSender(new Configuration("configGmail.json")), "gmail.com", "smalcerztest", "465" }, 
				{new MailSender(new Configuration("configGmail2.json")), "asd.com", "smalcerztest2", "465" },
				{new SmsSender(new Configuration("configVianettSms.json")), "cpa.vianett.no", "smalcerzszymonn@gmail.com", "31337" }};
		return Arrays.asList(expectedOutputs);
	}

	@Test
	public void testIfTwoHostsAreEqual() {
		assertEquals(this.sender.getConfiguration().getHost(), 
				expectedHost);
	}
	
	@Test
	public void testIfTwoPortsAreEqual() {
		assertEquals(this.sender.getConfiguration().getPort(), 
				expectedPort);
	}
	
	
	@Test
	public void testIfTwoNicksAreEqual() {
		assertEquals(this.sender.getConfiguration().getNick(), 
				expectedNick);
	}
}
