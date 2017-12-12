package entities;

import java.util.List;

public class TwitterSubscription {
    private String authors;
    private String keywords;


    public TwitterSubscription(List<String> authors, List<String> keywords) {
        this(String.join(",", authors), String.join(",", keywords));
    }

    public TwitterSubscription(String authors, String keywords) {
        this.authors = authors;
        this.keywords = keywords;
    }


    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
