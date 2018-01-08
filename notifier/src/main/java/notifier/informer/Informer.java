package notifier.informer;


import java.io.IOException;
import java.util.List;


import javax.mail.MessagingException;

import org.json.simple.parser.ParseException;


import model.News;
import model.User;
import model.UserNewsDTO;
import notifier.exceptions.BadLengthTelephoneNumberException;
import notifier.message.Message;
import notifier.message.UglyMessage;


import notifier.senders.MailSender;
import notifier.senders.Sendable;
import notifier.senders.SmsSender;

public class Informer {
//	
//	@Inject
//	private NotifierDeamon notifierDeamon;
	
	private static Informer informer = new Informer();
	private Sendable gmailMailSender;
//	sms sender ma limit 5ciu wyslan z czego : 3 wykorzystane, wiec lepiej nie wysylac 
	@SuppressWarnings("unused")
	private Sendable vianettSmsSender;
	
	public Informer() {
		
		try {
			this.gmailMailSender = new MailSender("configGmail.json");
			this.vianettSmsSender = new SmsSender("configVianettSms.json");
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void informUser(UserNewsDTO userNewsDTO) {
		User user = userNewsDTO.getUser();
		List<News> news = userNewsDTO.getNewsList();
		Message msg = new UglyMessage();
		//BEGIN  TODO 
		
			//we want to generate message appropriate to user
			String message = msg.generateMessage(news);
		
		//END    TODO
		//BEGIN  TODO
		
			//we want to send with appropriate sender
			try {
				
				gmailMailSender.send(user.getEmail(), "mail about your iterests", message);
				 
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (BadLengthTelephoneNumberException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		//END    TODO
		

		
		
	}
	
	public static Informer getInstanceOfInformer() {
		return informer;
	}
	
}
