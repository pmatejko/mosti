package notifier.informer;


import java.io.IOException;
import org.json.simple.parser.ParseException;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import exceptions.SenderException;
import interfaces.IProvider;
import model.UserNewsDTO;
import notifier.message.MessageGenerator;
import notifier.message.UglyMessageGenerator;
import notifier.senders.MailSender;
import notifier.senders.Sendable;
import notifier.senders.SmsSender;
import notifier.senders.configuration.Configuration;

@Singleton
public class NotifierManager {
	

	private IProvider iprovider;
	Sendable gmailMailSender;
//	sms sender ma limit 5ciu wyslan z czego : 3 wykorzystane, wiec lepiej nie wysylac 
	Sendable vianettSmsSender;

	@Inject
	public NotifierManager(IProvider iprovider) {
		try {
			this.iprovider = iprovider;
			Configuration gmailConfiguration = new Configuration("configGmail.json");
			Configuration vianettConfiguration = new Configuration("configVianettSms.json");
			
			this.gmailMailSender = new MailSender(gmailConfiguration);
			this.vianettSmsSender = new SmsSender(vianettConfiguration);
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void subscribe() {
        this.iprovider
                .getUserNewsObservable()
                .subscribe(this::informUser);
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
	
	
}
