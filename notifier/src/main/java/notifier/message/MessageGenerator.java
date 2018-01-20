package notifier.message;

import java.util.List;

import model.News;

public abstract class MessageGenerator {
	
	protected String contact;
	protected String title;
	protected String content;
	protected List<News> news;
	
	public MessageGenerator() {
		
	}
	
	public MessageGenerator(List<News> news,String contact) {
		this.news = news;
		this.contact = contact;
	}
	
	public String getContact() {
		return this.contact;
	}
	
	public abstract String getTitle();
	
	public abstract String generateMessageContent();
}
