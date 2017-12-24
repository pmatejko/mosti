package notifier.senders;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ SenderCreationFailureTest.class, SenderCreationTest.class, MailSenderSendTest.class })
public class SenderTests {

}
