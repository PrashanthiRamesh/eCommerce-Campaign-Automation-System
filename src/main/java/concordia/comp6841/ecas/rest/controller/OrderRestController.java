package concordia.comp6841.ecas.rest.controller;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import concordia.comp6841.ecas.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderRestController {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	OrderService orderService;

	@RequestMapping(value = "/save/all", method = RequestMethod.GET)
	public String getOrders() throws IOException {

		HttpHeaders headers = new HttpHeaders();
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("woo-username", "prash");
		body.add("woo-password", "password");
		body.add("woo-email", "prash@gmail.com");
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<?> entity = new HttpEntity<Object>(body, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				"http://localhost/wordpress-spm-project/index.php/wp-json/campaignit/v1/woo/get/all/orders",
				HttpMethod.POST, entity, String.class);
		BufferedWriter writer = new BufferedWriter(new FileWriter("order.json"));
		writer.write(response.getBody());
		writer.close();
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<concordia.comp6841.ecas.entity.COrder>> typeReference = new TypeReference<List<concordia.comp6841.ecas.entity.COrder>>() {
		};
		InputStream inputStream = new DataInputStream(new FileInputStream("order.json"));
		try {
			List<concordia.comp6841.ecas.entity.COrder> orders = mapper.readValue(inputStream, typeReference);
			orderService.save(orders);
			return "Yay";
		} catch (IOException e) {
			return "Nah"+ e.getMessage();
		}
		
	}

}
