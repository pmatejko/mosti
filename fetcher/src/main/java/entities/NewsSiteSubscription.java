package entities;

import java.util.List;

public class NewsSiteSubscription {
    private String newsSites;
    private String keywords;


    public NewsSiteSubscription(List<String> newsSites, List<String> keywords) {
        this(String.join(",", newsSites), String.join(",", keywords));
    }

    public NewsSiteSubscription(String newsSites, String keywords) {
        this.newsSites = newsSites;
        this.keywords = keywords;
    }


    public String getNewsSites() {
        return newsSites;
    }

    public void setNewsSites(String newsSites) {
        this.newsSites = newsSites;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
