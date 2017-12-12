package entities.DataDownloaders;

import javax.json.JsonObject;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface NewsDownloader {

    JsonObject getNews(String topic) throws IOException,InterruptedException, ExecutionException;

}
