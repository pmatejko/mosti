package util;

import com.google.inject.Singleton;
import interfaces.IPropertiesManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Singleton
public class PropertiesManager implements IPropertiesManager {
    private static final String DATA_PROVIDER_PROPERTIES_FILE_NAME = "data-provider.properties";

    private final Properties properties;

    public PropertiesManager() {
        try {
            InputStream dataProviderPropertiesStream = Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream(DATA_PROVIDER_PROPERTIES_FILE_NAME);

            properties = new Properties();
            properties.load(dataProviderPropertiesStream);
        } catch (IOException e) {
            throw new RuntimeException("Error with loading properties from " +
                    DATA_PROVIDER_PROPERTIES_FILE_NAME + " file", e);
        }
    }

    @Override
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
