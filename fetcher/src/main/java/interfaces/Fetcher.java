package interfaces;

import dto.SubscriptionDTO;
import model.News;

import java.util.List;

public interface Fetcher {
    List<News> fetch(SubscriptionDTO subscription) throws Exception;
}
