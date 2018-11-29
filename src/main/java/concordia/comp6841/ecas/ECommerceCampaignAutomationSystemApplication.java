package concordia.comp6841.ecas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;


@EnableAutoConfiguration
@SpringBootApplication
@EnableScheduling
public class ECommerceCampaignAutomationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceCampaignAutomationSystemApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
