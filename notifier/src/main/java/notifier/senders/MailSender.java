package notifier.senders;



import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.mail.smtp.SMTPTransport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.Security;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;




public class MailSender implements Sendable{
	
	private String nick;
	private String password;
	private String host;
	private String port;
	
	
	

	public MailSender(String configFilePath) {
		
		InputStream inputStream = MailSender.class.getResourceAsStream(configFilePath);
	 	BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		JSONParser parser = new JSONParser();
		
		try {
			
			Object obj = parser.parse(reader);
			JSONObject jsonObject = (JSONObject) obj;
			
			this.nick = (String) jsonObject.get("nick");
			this.password = (String) jsonObject.get("password");
			this.host = (String) jsonObject.get("host");
			this.port = (String) jsonObject.get("port");
			
			System.out.println(nick);
			System.out.println(password);
			System.out.println(host);
			System.out.println(port);

			
			reader.close();
			
			
		} catch (IOException | ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@SuppressWarnings("restriction")
	public void send(String contact, String title, String message) {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtps.host", "smtp." + this.host);
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", this.port);
        props.setProperty("mail.smtp.socketFactory.port", this.port);
        props.setProperty("mail.smtps.auth", "true");


        props.put("mail.smtps.quitwait", "false");

        Session session = Session.getInstance(props, null);

        // -- Create a new message --
        final MimeMessage msg = new MimeMessage(session);

        // -- Set the FROM and TO fields --
        try {
        	
		msg.setFrom(new InternetAddress(this.nick + "@" + this.host));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(contact, false));

//        if (ccEmail.length() > 0) {
//            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail, false));
//        }

        msg.setSubject(title);
        msg.setText(message, "utf-8");
        msg.setSentDate(new Date());

        SMTPTransport t = (SMTPTransport)session.getTransport("smtps");

        t.connect("smtp." + this.host, this.nick, this.password);
        t.sendMessage(msg, msg.getAllRecipients());      
        t.close();
        
        } catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
