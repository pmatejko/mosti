package notifier.informer;

import java.util.Iterator;

import model.News;
import model.UserNewsDTO;
import notifier.User.User;
import notifier.User.User.WayOfNotifying;
import notifier.senders.MailSender;
import notifier.senders.Sendable;
import notifier.senders.SmsSender;

public class Informer {
	
	private static Informer informer = new Informer();
	Sendable gmailMailSender;
//	sms sender ma limit 5ciu wyslan z czego : 3 wykorzystane, wiec lepiej nie wysylac 
	Sendable vianettSmsSender;
	
	
	private Informer() {
		this.gmailMailSender = new MailSender("configGmail.json");
		this.vianettSmsSender = new SmsSender("configVianettSms.json");
	}
	
	
	public void informUser(UserNewsDTO userNewsDTO) {
		User user = userNewsDTO.getUser();
		
		Iterator<WayOfNotifying> iteratorOfWayfOfNotifying = user.getWaysOfNotifying();
		
		while(iteratorOfWayfOfNotifying.hasNext()) {
			WayOfNotifying way = iteratorOfWayfOfNotifying.next();
			
			if(way == WayOfNotifying.EMAIL) {
				Iterator<News> iteratorOfNews = userNewsDTO.getNewsList().iterator();
				String content = "";
				
				while(iteratorOfNews.hasNext()) {
					News news = iteratorOfNews.next();
					
					content += "/n/n/n";
					
					content += news.getContent();
				}
				
				
				
				gmailMailSender.send(user.getEmail(), "mail about your iterests", content);
			}else if(way == WayOfNotifying.SMS){
				//nie zaimplementowane bo zostaly 2 darmowe smsy do wyslania i nie chce zeby ktos odpalil :DD
			}
		}
	}
	
	public static Informer getInformer() {
		return informer;
	}
	
}
