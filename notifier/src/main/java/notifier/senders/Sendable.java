package notifier.senders;

public interface Sendable {
	public void Send(String contact, String title, String message);
}
