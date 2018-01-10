package notifier.message;

import java.util.List;

import model.News;
import model.User;
import model.UserNewsDTO;

public abstract class MessageGenerator {
	
	protected String contact;
	protected String title;
	protected String content;
	protected List<News> news;
	protected User user;
	
	public MessageGenerator() {
		
	}
	
	public MessageGenerator(UserNewsDTO userNewsDTO) {
		this.news = userNewsDTO.getNewsList();
		this.user = userNewsDTO.getUser();
	}
	
	public String getEmail() {
		return this.user.getEmail();
	}
	
	public abstract String getContact();
	
	public abstract String getTitle();
	
	public abstract String generateMessageContent();
}
