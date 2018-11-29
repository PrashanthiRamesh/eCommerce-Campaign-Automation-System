package concordia.comp6841.ecas.component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import concordia.comp6841.ecas.entity.AbandonedCart;
import concordia.comp6841.ecas.entity.ActiveCustomer;
import concordia.comp6841.ecas.entity.COrder;
import concordia.comp6841.ecas.entity.Campaign;
import concordia.comp6841.ecas.entity.InactiveCustomer;
import concordia.comp6841.ecas.entity.OrderItems;
import concordia.comp6841.ecas.repository.AbandonedCartRepository;
import concordia.comp6841.ecas.repository.ActiveCustomerRepository;
import concordia.comp6841.ecas.repository.CampaignRepository;
import concordia.comp6841.ecas.repository.InactiveCustomerRepository;
import concordia.comp6841.ecas.repository.OrderRepository;

@Component
public class ScheduledCampaigns {

	private static final Logger log = LoggerFactory.getLogger(ScheduledCampaigns.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	private CampaignRepository campaignRepository;

	@Autowired
	private AbandonedCartRepository abandonedCartRepository;

	@Autowired
	private ActiveCustomerRepository activeCustomerRepository;

	@Autowired
	private InactiveCustomerRepository inactiveCustomerRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	// every 10 mins for testing (in reality it will be every day at 12pm) cron="0 0
	// 12 * * ?"

	@Scheduled(fixedRate = 360000)
	public void reportCurrentTime() throws AddressException, MessagingException, IOException {
		log.info("The time is now {}", dateFormat.format(new Date()));
		//sendmail();
	}

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

		// DONE: get campaigns and iterate them to get customer_group, set mail subject
		// DONE: check if campaign is within start and end
		// DONE: get customer group model based on customer_group
		// DONE: iterate through customer group and get email
		// DONE: get customer email and get orders and order items based on them
		// DONE: set body= dear first name last name, body from campaign

		if (!campaignRepository.findAll().isEmpty()) {
			log.info(campaignRepository.findAll().toString());
			for (Campaign campaign : campaignRepository.findAll()) {
				msg.setSubject(campaign.getSubject());
				log.info("we are inside");
				if (campaign.getStart().compareTo(LocalDateTime.now())
						* LocalDateTime.now().compareTo(campaign.getEnd()) >= 0) {
					String customer_group = campaign.getCustomer_group();
					switch (customer_group) {
					case "abandoned_cart":
						if (!abandonedCartRepository.findAll().isEmpty()) {
							for (AbandonedCart abandonedCart : abandonedCartRepository.findAll()) {
								msg.setRecipients(Message.RecipientType.TO,
										InternetAddress.parse(abandonedCart.getEmail()));
								String order_items="";
								if(!orderRepository.findAll().isEmpty()) {
									for(COrder cOrder : orderRepository.findAll()) {
										if(!cOrder.getOrder_items().isEmpty()){
											order_items="Order Items:\n";
											for(OrderItems orderItems: cOrder.getOrder_items()) {
												order_items+="- "+orderItems.getProduct_name();
											}
										}
									}
								}
								msg.setText(
										"Dear " + abandonedCart.getFirst_name() + " " + abandonedCart.getFirst_name()
												+ ",\n\n" + campaign.getBody()+"\nYour" +order_items+ "\n\nThanks,\nTeam SPM 1");
								msg.setSentDate(new Date());
								Transport.send(msg);
								log.info("Abandoned cart Campaign Sent");
							}
						}
						break;
					case "active_customers":
						if (!activeCustomerRepository.findAll().isEmpty()) {
							for (ActiveCustomer activeCustomer : activeCustomerRepository.findAll()) {
								msg.setRecipients(Message.RecipientType.TO,
										InternetAddress.parse(activeCustomer.getEmail()));
								String order_items="";
								if(!orderRepository.findAll().isEmpty()) {
									for(COrder cOrder : orderRepository.findAll()) {
										if(!cOrder.getOrder_items().isEmpty()){
											order_items="Order Items:\n";
											for(OrderItems orderItems: cOrder.getOrder_items()) {
												order_items+="- "+orderItems.getProduct_name();
											}
										}
									}
								}
								msg.setText(
										"Dear " + activeCustomer.getFirst_name() + " " + activeCustomer.getFirst_name()
												+ ",\n\n" + campaign.getBody()+"\nYour" +order_items+ "\n\nThanks,\nTeam SPM 1");
								msg.setSentDate(new Date());
								Transport.send(msg);
								log.info("Active Campaign Sent");
							}
						}

						break;
					case "inactive_customers":
						if (!inactiveCustomerRepository.findAll().isEmpty()) {
							for (InactiveCustomer inactiveCustomer : inactiveCustomerRepository.findAll()) {
								msg.setRecipients(Message.RecipientType.TO,
										InternetAddress.parse(inactiveCustomer.getEmail()));
								String order_items="";
								if(!orderRepository.findAll().isEmpty()) {
									for(COrder cOrder : orderRepository.findAll()) {
										if(!cOrder.getOrder_items().isEmpty()){
											order_items="Order Items:\n";
											for(OrderItems orderItems: cOrder.getOrder_items()) {
												order_items+="- "+orderItems.getProduct_name();
											}
										}
									}
								}
								msg.setText("Dear " + inactiveCustomer.getFirst_name() + " "
										+ inactiveCustomer.getFirst_name() + ",\n\n" + campaign.getBody()
										+"\nYour" +order_items+"\n\nThanks,\nTeam SPM 1");
								msg.setSentDate(new Date());
								Transport.send(msg);
								log.info("Inactive Campaign Sent");
							}
						}

						break;
					}
				} else {
					log.info("not within date start and end");
				}

			}
		}

	}
}
