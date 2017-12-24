package notifier.message;

import java.util.Iterator;
import model.News;
import model.UserNewsDTO;

public class UglyMessageGenerator extends MessageGenerator{

	public UglyMessageGenerator(UserNewsDTO userNewsDTO) {
		super(userNewsDTO);
	}

	@Override
	public String generateMessageContent() {
		
		Iterator<News> newsIterator = news.iterator();
		String message ="";
		message += "\nMosti api wants to inform you about new notifications: \n\n";
		
		while(newsIterator.hasNext()) {
			News temp = newsIterator.next();
			
			message += "_______________________________________________________\n\n\n";
			message += "source of notification: ";
			message += temp.getUrl();
			message += "\n\n\ncontent:";
			message += temp.getContent();
			
		}
		
		return message;
	}

	@Override
	public String getContact() {
		return this.getEmail();
	}

	@Override
	public String getTitle() {
		return "ugly title";
	}

}
