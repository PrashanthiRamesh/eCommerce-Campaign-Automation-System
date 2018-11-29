package concordia.comp6841.ecas.component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledCampaigns {

	private static final Logger log = LoggerFactory.getLogger(ScheduledCampaigns.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	// every 10 mins for testing (in reality it will be every day at 12pm) cron="0 0
	// 12 * * ?"

	@Scheduled(fixedRate = 20000)
	public void reportCurrentTime() throws AddressException, MessagingException, IOException {
		log.info("The time is now {}", dateFormat.format(new Date()));
		sendmail();
		log.info("Yay! Mail sent");
	}
	
	//send mail
	
	private void sendmail() throws AddressException, MessagingException, IOException {
		   Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.port", "587");
		   
		   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("soenteam1@gmail.com", "teamspm6841");
		      }
		   });
		   Message msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress("soenteam1@gmail.com", false));

		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("test.user.soen6841+1@gmail.com"));
		   msg.setSubject("Tutorials point email");
		   msg.setContent("Tutorials point email", "text/html");
		   msg.setSentDate(new Date());

		   Transport.send(msg);   
		}
}
