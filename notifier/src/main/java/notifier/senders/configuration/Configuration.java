package notifier.senders.configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import notifier.senders.MailSender;

public class Configuration {
	
	private String pathToFile;
	private String password;
	
	private String nick;
	private String host;
	private String port;
	
	public Configuration(String pathToFile) throws IOException, ParseException {
		this.pathToFile = pathToFile;
		this.readConfFromFile(pathToFile);
	}

	public String getPathToFile() {
		return pathToFile;
	}

	public String getPassword() {
		return password;
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
	
	private void readConfFromFile(String configFilePath) throws IOException, ParseException {
    	
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
}
