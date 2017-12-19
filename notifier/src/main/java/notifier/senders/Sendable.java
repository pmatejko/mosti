package notifier.senders;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import exceptions.BadLengthTelephoneNumberException;

public interface Sendable {
	public void send(String contact, String title, String message) throws AddressException, MessagingException, BadLengthTelephoneNumberException;
}
