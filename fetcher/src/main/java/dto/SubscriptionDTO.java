package dto;

import model.Preferences;

public class SubscriptionDTO {
    private final String newsSource;
    private final String keyword;


    public SubscriptionDTO(String newsSource, String keyword) {
        this.newsSource = newsSource;
        this.keyword = keyword;
    }

    public SubscriptionDTO(Preferences preferences) {
        this.newsSource = preferences.getNewsSource();
        this.keyword = preferences.getKeyword();
    }


    public String getNewsSource() {
        return newsSource;
    }

    public String getKeyword() {
        return keyword;
    }
}
