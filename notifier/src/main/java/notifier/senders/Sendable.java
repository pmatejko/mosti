package notifier.senders;

public interface Sendable {
	public void send(String contact, String title, String message);
}
