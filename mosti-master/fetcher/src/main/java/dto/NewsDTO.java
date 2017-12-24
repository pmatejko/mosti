package dto;


import model.News;

import java.util.Collections;
import java.util.List;

public class NewsDTO {
    private final List<News> newsList;


    public NewsDTO(List<News> newsList) {
        this.newsList = Collections.unmodifiableList(newsList);
    }


    public List<News> getNewsList() {
        return newsList;
    }

    public void addNews(News news){
        this.newsList.add(news);
    }
}



