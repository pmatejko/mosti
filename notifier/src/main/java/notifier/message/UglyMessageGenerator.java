package notifier.message;

import java.util.Iterator;
import java.util.List;

import model.News;

public class UglyMessageGenerator extends MessageGenerator{
	
	
	public UglyMessageGenerator() {
		
	}
	
	public UglyMessageGenerator(List<News> news, String contact) {
		super(news,contact);
	}

	@Override
	public String generateMessageContent() {
		
		Iterator<News> newsIterator = news.iterator();
		String message ="";
		StringBuilder sb = new StringBuilder(message);
		sb.append("\nMosti api wants to inform you about new notifications: \n\n");
		
		while(newsIterator.hasNext()) {
			News temp = newsIterator.next();
			
			sb.append("\n\n\n_______________________________________________________\n\n\n");
			sb.append("source of notification: \n\n");
			sb.append(temp.getUrl());
			sb.append("\n\n\ncontent:");
			sb.append(temp.getContent());
			
		}
		
		return sb.toString();
	}

	@Override
	public String getTitle() {
		return "ugly title";
	}

}
