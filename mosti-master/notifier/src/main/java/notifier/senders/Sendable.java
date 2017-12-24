package notifier.senders;

import exceptions.SenderException;

public interface Sendable {
	public void send(String contact, String title, String message) throws SenderException;
}
