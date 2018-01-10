package notifier.informer;


import java.io.IOException;
import org.json.simple.parser.ParseException;

import exceptions.SenderException;
import model.UserNewsDTO;
import notifier.message.MessageGenerator;
import notifier.message.UglyMessageGenerator;
import notifier.senders.MailSender;
import notifier.senders.Sendable;
import notifier.senders.SmsSender;
import notifier.senders.configuration.Configuration;

public class Informer {
	
	private static Informer informer = new Informer();
	Sendable gmailMailSender;
//	sms sender ma limit 5ciu wyslan z czego : 3 wykorzystane, wiec lepiej nie wysylac 
	Sendable vianettSmsSender;
	
	
	private Informer() {
		try {

			Configuration gmailConfiguration = new Configuration("configGmail.json");
			Configuration vianettConfiguration = new Configuration("configVianettSms.json");
			
			this.gmailMailSender = new MailSender(gmailConfiguration);
			this.vianettSmsSender = new SmsSender(vianettConfiguration);
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void informUser(UserNewsDTO userNewsDTO) throws SenderException {
		
		
		
		//BEGIN  TODO 
		
			//we want to generate message appropriate to user or appropirate to kind of information
			MessageGenerator msg = new UglyMessageGenerator(userNewsDTO);
			
			
		//END    TODO
		//BEGIN  TODO
		
			//we want to send with appropriate sender
			
			
			gmailMailSender.send(msg);
			

			
		//END    TODO
		

		
		
	}
	
	public static Informer getInstanceOfInformer() {
		return informer;
	}
	
}
