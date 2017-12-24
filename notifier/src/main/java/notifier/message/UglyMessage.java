package notifier.message;

import java.util.Iterator;
import java.util.List;

import model.News;

public class UglyMessage implements Message{

	@Override
	public String generateMessage(List<News> news) {
		
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
	
}
