package dataDownloaders;

import java.io.StringReader;
import java.util.Scanner;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.util.concurrent.ExecutionException;


public final class TwitterDownloader implements NewsDownloader {

    private OAuth1AccessToken accessToken = null;
    private final OAuth10aService service = new ServiceBuilder("A3HiwxEgAvPVTfrgrbh759dzj")
            .apiSecret("yFWPALPeQJsElynktj8SYnfxpWiNZRFegZAJKFAr9TyN3S602D")
            .build(TwitterApi.instance());


    public TwitterDownloader(){}

    public void authorize() throws IOException, InterruptedException, ExecutionException{

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

    public JsonObject getNews(String URL) throws IOException, InterruptedException, ExecutionException{
        final OAuthRequest request = new OAuthRequest(Verb.GET, URL);
        service.signRequest(accessToken, request);
        final Response response = service.execute(request);

        JsonReader reader = Json.createReader(new StringReader(response.getBody()));
        JsonObject page = reader.readObject();
        reader.close();

        return page;
    }

}
