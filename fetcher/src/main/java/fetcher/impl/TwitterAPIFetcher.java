package fetcher.impl;

import dataDownloaders.TwitterDownloader;
import interfaces.Fetcher;
import model.News;
import model.Preferences;

import javax.json.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;


public class TwitterAPIFetcher implements Fetcher {

    private static final String apiUrl = "https://api.twitter.com/1.1/search/tweets.json?q=";
    private static final TwitterDownloader tweetDownloader = new TwitterDownloader();

    @Override
    public List<News> fetch(Preferences preferences) throws IOException, InterruptedException, ExecutionException, ParseException {
        String queryString = buildQueryString(preferences);

        if(!tweetDownloader.haveAccessToken())
            tweetDownloader.authorize();

        JsonObject tweets = tweetDownloader.getNews(queryString);

        JsonArray jsonArticles = tweets.getJsonArray("statuses");

        return parseJsonTweetsArray(jsonArticles, preferences);
    }

    private String buildQueryString(Preferences preferences) throws UnsupportedEncodingException {
        StringBuilder stringBuilder = new StringBuilder(100);
        stringBuilder.append(apiUrl);

        if (preferences.getKeyword() != null) {
            stringBuilder.append(URLEncoder.encode(preferences.getKeyword(), StandardCharsets.UTF_8.toString()));
        }

        if(preferences.getNewsSource() != null){
            stringBuilder.append(URLEncoder.encode(" from:" + preferences.getNewsSource(), StandardCharsets.UTF_8.toString()));
        }

        return stringBuilder.toString();
    }


    private List<News> parseJsonTweetsArray(JsonArray jsonArticles, Preferences preferences) throws ParseException {
        List<News> tweets = new LinkedList<>();

        String dateFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";

        for (JsonValue jsonValue : jsonArticles) {
            if (jsonValue.getValueType() == JsonValue.ValueType.OBJECT) {
                JsonObject jsonArticle = (JsonObject) jsonValue;

                String url = "https://twitter.com/statuses/" + jsonArticle.getString("id_str");
                String content = jsonArticle.getString("text");
                Date timestamp =  new SimpleDateFormat(dateFormat, Locale.ENGLISH).parse(jsonArticle.getString("created_at"));

                tweets.add(new News(preferences, url, content, timestamp));
            }
        }

        return tweets;
    }

//    public static void main(String args[]) {
//        try {
//            TwitterAPIFetcher x = new TwitterAPIFetcher();
//
//            Preferences preferences = new Preferences();
//            preferences.setKeyword("Audi");
//            //preferences.setNewsSource("Audi");
//            List<News> lista = x.fetch(preferences);
//
//            for (News tweet: lista) {
//                System.out.println(tweet.getUrl());
//                System.out.println(tweet.getContent());
//                System.out.println(tweet.getTimestamp());
//                System.out.println();
//            }
//        }
//        catch(Exception e){
//            System.out.println("Exception!");
//            e.printStackTrace();
//        }
//
//    }
}
