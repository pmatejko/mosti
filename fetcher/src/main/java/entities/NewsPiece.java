package entities;

import java.time.LocalDate;

public abstract class NewsPiece {
    private String url;
    private String content;
    private LocalDate timestamp;


    public NewsPiece(String url, String content, LocalDate timestamp) {
        this.url = url;
        this.content = content;
        this.timestamp = timestamp;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }
}
