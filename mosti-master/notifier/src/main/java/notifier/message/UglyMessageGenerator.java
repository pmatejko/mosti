package notifier.message;

import java.util.Iterator;
import model.News;
import model.UserNewsDTO;

public class UglyMessageGenerator extends MessageGenerator{
	
	
	public UglyMessageGenerator() {
		
	}
	
	public UglyMessageGenerator(UserNewsDTO userNewsDTO) {
		super(userNewsDTO);
	}

	@Override
	public String generateMessageContent() {
		
		Iterator<News> newsIterator = news.iterator();
		String message ="";
		StringBuilder sb = new StringBuilder(message);
		sb.append("\nMosti api wants to inform you about new notifications: \n\n");
		
		while(newsIterator.hasNext()) {
			News temp = newsIterator.next();
			
			sb.append("_______________________________________________________\n\n\n");
			sb.append("source of notification: ");
			sb.append(temp.getUrl());
			sb.append("\n\n\ncontent:");
			sb.append(temp.getContent());
			
		}
		
		return sb.toString();
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
