package fetcher.impl;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.builder.api.DefaultApi10a;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.OAuth10aService;
import exceptions.FetchingException;
import interfaces.Fetcher;
import model.News;
import model.Preferences;

import javax.json.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;


public class TwitterAPIFetcher implements Fetcher {
    private static final String apiUrl = "https://api.twitter.com/1.1/search/tweets.json?q=";

    private static OAuth1AccessToken accessToken = null;
    private final OAuth10aService service = new ServiceBuilder("A3HiwxEgAvPVTfrgrbh759dzj")
            .apiSecret("yFWPALPeQJsElynktj8SYnfxpWiNZRFegZAJKFAr9TyN3S602D")
            .build(TwitterApi.instance());


    @Override
    public List<News> fetch(Preferences preferences) throws FetchingException {
        try {
            String queryString = buildQueryString(preferences);

            if (accessToken == null)
                authorize();

            final OAuthRequest request = new OAuthRequest(Verb.GET, queryString);
            service.signRequest(accessToken, request);
            final Response response = service.execute(request);

            JsonReader reader = Json.createReader(new StringReader(response.getBody()));
            JsonObject tweets = reader.readObject();
            reader.close();

            JsonArray jsonTweets = tweets.getJsonArray("statuses");

            return parseJsonTweetsArray(jsonTweets, preferences);
        } catch (IOException | ExecutionException | ParseException | InterruptedException e) {
            throw new FetchingException(e);
        }
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

    private void authorize() throws IOException, InterruptedException, ExecutionException{
        final Scanner in = new Scanner(System.in);

        // Obtain the Request Token
        final OAuth1RequestToken requestToken = service.getRequestToken();

        //to trzeba zmienic, przy autentykacji trzeba przekopiowac kod z neta
        System.out.println("Now go and authorize ScribeJava here:");
        System.out.println(service.getAuthorizationUrl(requestToken));
        System.out.println("And paste the verifier here");
        System.out.print(">>");

        final String oauthVerifier = in.nextLine();

        // Trade the Request Token and Verfier for the Access Token
        accessToken = service.getAccessToken(requestToken, oauthVerifier);
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



    private static class TwitterApi extends DefaultApi10a {
        private static final String AUTHORIZE_URL = "https://api.twitter.com/oauth/authorize?oauth_token=%s";
        private static final String REQUEST_TOKEN_RESOURCE = "api.twitter.com/oauth/request_token";
        private static final String ACCESS_TOKEN_RESOURCE = "api.twitter.com/oauth/access_token";

        protected TwitterApi() {
        }

        private static class InstanceHolder {
            private static final TwitterApi INSTANCE = new TwitterApi();
        }

        public static TwitterApi instance() {
            return TwitterApi.InstanceHolder.INSTANCE;
        }

        @Override
        public String getAccessTokenEndpoint() {
            return "https://" + ACCESS_TOKEN_RESOURCE;
        }

        @Override
        public String getRequestTokenEndpoint() {
            return "https://" + REQUEST_TOKEN_RESOURCE;
        }

        @Override
        public String getAuthorizationUrl(OAuth1RequestToken requestToken) {
            return String.format(AUTHORIZE_URL, requestToken.getToken());
        }

        /**
         * Twitter 'friendlier' authorization endpoint for OAuth.
         *
         * Uses SSL.
         */
        public static class Authenticate extends TwitterApi {

            private static final String AUTHENTICATE_URL = "https://api.twitter.com/oauth/authenticate?oauth_token=%s";

            private Authenticate() {
            }

            private static class InstanceHolder {
                private static final Authenticate INSTANCE = new Authenticate();
            }

            public static Authenticate instance() {
                return Authenticate.InstanceHolder.INSTANCE;
            }

            @Override
            public String getAuthorizationUrl(OAuth1RequestToken requestToken) {
                return String.format(AUTHENTICATE_URL, requestToken.getToken());
            }
        }
    }

    public static void main(String args[]) {
        try {
            TwitterAPIFetcher x = new TwitterAPIFetcher();

            Preferences preferences = new Preferences();
            //preferences.setKeyword("Audi");
            preferences.setNewsSource("realDonaldTrump");
            List<News> lista = x.fetch(preferences);

            for (News tweet: lista) {
                System.out.println(tweet.getUrl());
                System.out.println(tweet.getContent());
                System.out.println(tweet.getTimestamp());
                System.out.println();
            }
        }
        catch(Exception e){
            System.out.println("Exception!");
            e.printStackTrace();
        }
    }
}
