package notifier.message;

import java.util.List;

import model.News;

public interface Message {
	public String generateMessage(List<News> news);
}
