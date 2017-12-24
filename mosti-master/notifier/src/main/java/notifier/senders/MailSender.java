package notifier.senders;



import org.json.simple.parser.ParseException;

import com.sun.mail.smtp.SMTPTransport;

import exceptions.SenderException;
import notifier.message.MessageGenerator;

import java.io.IOException;
import java.security.Security;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;




public class MailSender extends Sender{
	
	
	private Properties props;

	public MailSender(String configFilePath) throws IOException, ParseException {
		
		super(configFilePath);
		this.props = System.getProperties();
	}

		
	public void send(MessageGenerator messageGenerator) throws SenderException {
      
		if(!this.configured) {
			this.configure();
		}

        	
		try {
			Session session = Session.getInstance(props, null);

	        final MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(this.nick + "@" + this.host));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(messageGenerator.getContact(), false));
	        msg.setSubject(messageGenerator.getTitle());
	        msg.setText(messageGenerator.generateMessageContent(), "utf-8");
	        msg.setSentDate(new Date());
	        SMTPTransport t = (SMTPTransport)session.getTransport("smtps");

	        t.connect("smtp." + this.host, this.nick, this.password);
	        t.sendMessage(msg, msg.getAllRecipients());      
	        t.close();
		} catch (SendFailedException e) {
			throw new SenderException(e);
		} catch (MessagingException e) {
			throw new SenderException(e);
		}
        

        
    }

	@SuppressWarnings("restriction")
	@Override
	public synchronized void configure() {
		  if(!this.configured) {
			  Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			  final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
			// Get a Properties object
			  this.props.setProperty("mail.smtps.host", "smtp." + this.host);
		      this.props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		      this.props.setProperty("mail.smtp.socketFactory.fallback", "false");
		      this.props.setProperty("mail.smtp.port", this.port);
		      this.props.setProperty("mail.smtp.socketFactory.port", this.port);
		      this.props.setProperty("mail.smtps.auth", "true");
		      this.props.put("mail.smtps.quitwait", "false");
		      this.configured = true;
		  }
		
	}
}
