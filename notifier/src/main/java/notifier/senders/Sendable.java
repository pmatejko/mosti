package notifier.senders;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import notifier.exceptions.BadLengthTelephoneNumberException;

public interface Sendable {
	void send(String contact, String title, String message) throws AddressException, MessagingException, BadLengthTelephoneNumberException;
}
