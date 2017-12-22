package notifier.senders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public abstract class Sender implements Sendable{ 

    protected String nick;
    protected String password;
    protected String host;
    protected String port;
    protected boolean configured;
    
    
    public Sender(String configFilePath) throws IOException, ParseException
    { 
		this.readConfFromFile(configFilePath);
		this.configured = false;
    }
    
    public void readConfFromFile(String configFilePath) throws IOException, ParseException {
    	
    	InputStream inputStream = MailSender.class.getResourceAsStream(configFilePath);
	 	BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(reader);
		JSONObject jsonObject = (JSONObject) obj;
		
		this.nick = (String) jsonObject.get("nick");
		this.password = (String) jsonObject.get("password");
		this.host = (String) jsonObject.get("host");
		this.port = (String) jsonObject.get("port");
		reader.close();
    }

	
	
	public String getNick() {
		return nick;
	}

	public String getHost() {
		return host;
	}

	public String getPort() {
		return port;
	}
	
    public abstract void configure();
}
