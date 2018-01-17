package notifier.informer.struct;

import exceptions.SenderException;
import notifier.message.UglyMessageGenerator;
import notifier.senders.Sender;

public class Struct {
	
	
	private UglyMessageGenerator message;
	private Sender sender;
	
	public Struct(UglyMessageGenerator uglyMessageGenerator, Sender sender) {
		super();
		this.message = uglyMessageGenerator;
		this.sender = sender;
	}
	public UglyMessageGenerator getMessageGenerator() {
		return message;
	}
	public void setMessageGenerator(UglyMessageGenerator message) {
		this.message = message;
	}
	public Sender getSender() {
		return sender;
	}
	public void setSender(Sender sender) {
		this.sender = sender;
	}
	
	
	public void sendMessage() throws SenderException {
		this.sender.send(this.message);
	}
	
	

}
