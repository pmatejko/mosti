package fetcher.impl.connectors;


import exceptions.FetchingException;
import interfaces.Connector;
import interfaces.PropertiesManager;

import model.Preferences;

import javax.json.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import java.util.*;

import static java.nio.charset.StandardCharsets.UTF_8;

public class TwitterAPIConnector implements Connector {

    private static final String API_URL = "https://api.twitter.com/1.1/search/tweets.json?q=";
    private static final String BEARER_TOKEN_URL = "https://api.twitter.com/oauth2/token";

    private static final String POST = "POST";
    private static final String GET = "GET";

    private static final String AUTHORIZATION = "Authorization";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_LENGTH = "Content-Length";

    private static final String BASIC = "Basic ";
    private static final String BEARER = "Bearer ";
    private static final String CONTENT_TYPE_VALUE = "application/x-www-form-urlencoded;charset=UTF-8";
    private static final String BEARER_REQUEST_BODY = "grant_type=client_credentials";


    private final String ACCESS_TOKEN;


    public TwitterAPIConnector(PropertiesManager propertiesManager) {

        try {
            String consumerKey = propertiesManager.getProperty(PropertiesManager.Keys.TWITTER_API_KEY);
            String consumerSecret = propertiesManager.getProperty(PropertiesManager.Keys.TWITTER_API_SECRET);
            String base64Credentials = createEncodedCredentials(consumerKey, consumerSecret);

            HttpURLConnection connection = createAccessTokenConnection(base64Credentials);

            ACCESS_TOKEN = getAccessToken(connection);
        } catch (IOException | FetchingException e) {
            throw new RuntimeException(e);
        }
    }



    public JsonArray getData(Preferences preferences) throws FetchingException {
        try {
            String queryString = buildQueryString(preferences);

            HttpURLConnection connection = createQueryConnection(queryString);

            JsonObject jsonResponse = getJsonResponse(connection);
            return jsonResponse.getJsonArray("statuses");

        } catch (IOException e) {
            throw new FetchingException(e);
        }
    }


    private String createEncodedCredentials(String key, String secret) throws UnsupportedEncodingException {
        String consumerKey = URLEncoder.encode(key, UTF_8.toString());
        String consumerSecret = URLEncoder.encode(secret, UTF_8.toString());
        String credentials = consumerKey + ":" + consumerSecret;

        return Base64.getEncoder().encodeToString(credentials.getBytes());
    }


    private HttpURLConnection createAccessTokenConnection(String base64Credentials) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(BEARER_TOKEN_URL).openConnection();

        byte[] requestBody = BEARER_REQUEST_BODY.getBytes(UTF_8.toString());
        connection.setRequestMethod(POST);
        connection.setRequestProperty(AUTHORIZATION, BASIC + base64Credentials);
        connection.setRequestProperty(CONTENT_TYPE, CONTENT_TYPE_VALUE);
        connection.setRequestProperty(CONTENT_LENGTH, String.valueOf(requestBody.length));
        connection.setDoOutput(true);

        OutputStream os = connection.getOutputStream();
        os.write(requestBody);
        os.close();

        connection.connect();
        return connection;
    }


    private String getAccessToken(HttpURLConnection connection) throws IOException, FetchingException {
        if (connection.getResponseCode() == 200) {
            JsonObject response = getJsonResponse(connection);

            if ("bearer".equals(response.getString("token_type"))) {
                return response.getString("access_token");
            }
        }

        throw new FetchingException(connection.getResponseCode() + " - cannot acquire TwitterAPI access token");
    }


    private JsonObject getJsonResponse(HttpURLConnection connection) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        JsonReader jsonReader = Json.createReader(bufferedReader);
        JsonObject response = jsonReader.readObject();
        jsonReader.close();

        return response;
    }


    private String buildQueryString(Preferences preferences) throws UnsupportedEncodingException {
        StringBuilder queryBuilder = new StringBuilder(100);

        if (preferences.getKeyword() != null) {
            queryBuilder.append(preferences.getKeyword());
        }

        if (preferences.getKeyword() != null && preferences.getNewsSource() != null) {
            queryBuilder.append(" ");
        }

        if (preferences.getNewsSource() != null){
            queryBuilder.append("from:");
            queryBuilder.append(preferences.getNewsSource());
        }

        String encodedQuery = URLEncoder.encode(queryBuilder.toString(), UTF_8.toString());

        return API_URL + encodedQuery;
    }


    private HttpURLConnection createQueryConnection(String queryString) throws IOException {
        URL url = new URL(queryString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(GET);
        connection.setRequestProperty(AUTHORIZATION, BEARER + ACCESS_TOKEN);

        connection.connect();
        return connection;
    }

}
