package concordia.comp6841.ecas.rest.controller;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import concordia.comp6841.ecas.entity.ActiveCustomer;
import concordia.comp6841.ecas.entity.Customer;
import concordia.comp6841.ecas.entity.CustomerGroup;
import concordia.comp6841.ecas.entity.User;
import concordia.comp6841.ecas.repository.CustomerRepository;
import concordia.comp6841.ecas.service.AbandonedCartService;
import concordia.comp6841.ecas.service.CustomerGroupService;
import concordia.comp6841.ecas.service.UserService;

@RestController
@RequestMapping("/api/abandoned_cart")
public class AbandonedCartRestController {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private CustomerGroupService customerGroupService;

	@Autowired
	private UserService userService;

	@Autowired
	AbandonedCartService abandonedCartService;

	@Autowired
	CustomerRepository customerRepository;

	@RequestMapping(value = "/save/all", method = RequestMethod.GET)
	public String getAbandonedCart() throws IOException {

		HttpHeaders headers = new HttpHeaders();
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("woo-username", "prash");
		body.add("woo-password", "password");
		body.add("woo-email", "prash@gmail.com");
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<?> entity = new HttpEntity<Object>(body, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				"http://localhost/wordpress-spm-project/index.php/wp-json/campaignit/v1/woo/get/all/abandoned_carts",
				HttpMethod.POST, entity, String.class);
		BufferedWriter writer = new BufferedWriter(new FileWriter("abandoned_cart.json"));
		writer.write(response.getBody());
		writer.close();
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<concordia.comp6841.ecas.entity.AbandonedCart>> typeReference = new TypeReference<List<concordia.comp6841.ecas.entity.AbandonedCart>>() {
		};
		InputStream inputStream = new DataInputStream(new FileInputStream("abandoned_cart.json"));
		try {
			List<concordia.comp6841.ecas.entity.AbandonedCart> abandonedCarts = mapper.readValue(inputStream,
					typeReference);
			abandonedCartService.save(abandonedCarts);
			return "Yay";
		} catch (IOException e) {
			return "Nah" + e.getMessage();
		}

	}

	@RequestMapping(value = "/test/active", method = RequestMethod.GET)
	public List<ActiveCustomer> gettestactive(Principal principal) throws ParseException, JsonParseException, JsonMappingException, IOException {
		// get active_lastseen
		User user = userService.findByEmail(principal.getName());
		String x = "nah";

		CustomerGroup existing = customerGroupService.findByEmail(user.getEmail());

		Integer active_lastseen = existing.getActive_lastseen();
		DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		DateTime lastWeek = new DateTime().minusDays(active_lastseen);
		x = dateTimeFormatter.print(lastWeek);
		List<Customer> customers = customerRepository
				.findAllWithLastSeenBefore(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(x));
		ObjectMapper active_mapper = new ObjectMapper();
		BufferedWriter active_writer = new BufferedWriter(new FileWriter("active_customers.json"));
		active_writer.write(active_mapper.writeValueAsString(customers));
		active_writer.close();
		
		TypeReference<List<concordia.comp6841.ecas.entity.ActiveCustomer>> active_typeReference = new TypeReference<List<concordia.comp6841.ecas.entity.ActiveCustomer>>() {
		};
		InputStream active_inputStream = new DataInputStream(new FileInputStream("active_customers.json"));

		List<concordia.comp6841.ecas.entity.ActiveCustomer> activeCustomers = active_mapper.readValue(active_inputStream, active_typeReference);
		
		return activeCustomers;
	}

	@RequestMapping(value = "/test/inactive", method = RequestMethod.GET)
	public List<Customer> gettestinactive(Principal principal) throws ParseException {
		// get active_lastseen
		User user = userService.findByEmail(principal.getName());
		String x = "nah";

		CustomerGroup existing = customerGroupService.findByEmail(user.getEmail());

		Integer inactive_lastseen = existing.getInactive_lastseen();
		DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		DateTime lastWeek = new DateTime().minusDays(inactive_lastseen);
		x = dateTimeFormatter.print(lastWeek);
		List<Customer> customers = customerRepository
				.findAllWithLastSeenAfter(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(x));

		return customers;
	}

}
