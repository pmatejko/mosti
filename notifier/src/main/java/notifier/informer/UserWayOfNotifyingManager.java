package notifier.informer;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import com.google.inject.Singleton;

import notifier.senders.MailSender;
import notifier.senders.Sender;
import notifier.senders.SmsSender;
import notifier.senders.configuration.Configuration;

@Singleton
public class UserWayOfNotifyingManager {
	

	private Sender gmailMailSender;
//	sms sender ma limit 5ciu wyslan z czego : 3 wykorzystane, wiec lepiej nie wysylac 
	private Sender vianettSmsSender;
	
	
	public UserWayOfNotifyingManager() {
		
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
	
	public ArrayList<Sender> getListOfSenders(int waysOfNotifying){
		
		ArrayList<Sender> list = new ArrayList<>();
		
		if(waysOfNotifying%2 == 1) {
			list.add(this.gmailMailSender);
			System.out.println("ADDING GMAIL");
		}
		
		waysOfNotifying = shiftRight(waysOfNotifying);
		
		if(waysOfNotifying%2 == 1) {
			list.add(this.vianettSmsSender);
			System.out.println("ADDING SMS");
		}
		
		
		return list;
		
	}
	
	private int shiftRight(int binary) {
		
		binary = (binary - binary%2)/10;
		
		return binary;
	}
	
}
