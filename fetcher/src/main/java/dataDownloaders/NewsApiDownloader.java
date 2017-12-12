package dataDownloaders;

import java.net.*;
import java.io.*;
import javax.json.*;


public class NewsApiDownloader implements NewsDownloader {


    public JsonObject getNews(String topic) throws IOException{

        URL url = new URL("https://newsapi.org/v2/everything?" +
                "q=" + topic + "&" +
                "from=2017-12-11&" +
                "sortBy=popularity&" +
                "apiKey=cc36659c4c784994a77282ce4e4dc1ed"
        );

        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

        JsonReader reader = Json.createReader(in);
        JsonObject page = reader.readObject();
        reader.close();
        in.close();

        return page;
    }

}
