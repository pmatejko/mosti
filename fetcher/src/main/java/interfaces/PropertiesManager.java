package interfaces;

public interface PropertiesManager {
    String getProperty(String key);

    class Keys {
        public static final String NEWS_API_KEY = "NEWS_API_KEY";
        public static final String TWITTER_API_KEY = "TWITTER_API_KEY";
        public static final String TWITTER_API_SECRET = "TWITTER_API_SECRET";
    }
}
