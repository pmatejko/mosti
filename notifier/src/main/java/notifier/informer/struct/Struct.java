package notifier.informer.struct;

import exceptions.SenderException;
import notifier.message.MessageGenerator;
import notifier.senders.Sender;

public class Struct {
	
	
	private MessageGenerator messageGenerator;
	private Sender sender;
	
	public Struct(MessageGenerator messageGenerator, Sender sender) {
		super();
		this.messageGenerator = messageGenerator;
		this.sender = sender;
	}
	public MessageGenerator getMessageGenerator() {
		return messageGenerator;
	}
	public void setMessageGenerator(MessageGenerator message) {
		this.messageGenerator = message;
	}
	public Sender getSender() {
		return sender;
	}
	public void setSender(Sender sender) {
		this.sender = sender;
	}
	
	
	public void sendMessage() throws SenderException {
		this.sender.send(this.messageGenerator);
	}
	
	

}
