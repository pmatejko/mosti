package notifier.senders;

import exceptions.SenderException;
import notifier.message.MessageGenerator;

public interface Sendable {
	public void send(MessageGenerator messageGenerator) throws SenderException;
}
