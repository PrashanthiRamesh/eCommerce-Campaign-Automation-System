package concordia.comp6841.ecas.controller;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import concordia.comp6841.ecas.service.AbandonedCartService;
import concordia.comp6841.ecas.service.CategoryService;
import concordia.comp6841.ecas.service.CustomerService;
import concordia.comp6841.ecas.service.OrderService;
import concordia.comp6841.ecas.service.ProductService;

@Controller
@RequestMapping("/import")
public class ImportController {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	CustomerService customerService;

	@Autowired
	OrderService orderService;

	@Autowired
	AbandonedCartService abandonedCartService;

	@PostMapping("/start")
	public String startImport(Model model, @RequestParam("woo_username") String username,
			@RequestParam("woo_password") String password, @RequestParam("woo_email") String email) throws IOException {
		if (syncCategories(username, password, email) && syncProducts(username, password, email)
				&& syncCustomers(username, password, email) && syncOrders(username, password, email)
				&& syncAbandonedCarts(username, password, email)) {
			return "redirect:/settings?success";
		} else {
			return "redirect:/settings?fail";
		}
	}

	public Boolean syncProducts(String username, String password, String email) {
		HttpHeaders headers = new HttpHeaders();
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("woo-username", username);
		body.add("woo-password", password);
		body.add("woo-email", email);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<?> entity = new HttpEntity<Object>(body, headers);
		try {
			ResponseEntity<String> response = restTemplate.exchange(
					"http://localhost/wordpress-spm-project/index.php/wp-json/campaignit/v1/woo/get/all/products",
					HttpMethod.POST, entity, String.class);
			BufferedWriter writer = new BufferedWriter(new FileWriter("product.json"));
			writer.write(response.getBody());
			writer.close();
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<concordia.comp6841.ecas.entity.Product>> typeReference = new TypeReference<List<concordia.comp6841.ecas.entity.Product>>() {
			};
			InputStream inputStream = new DataInputStream(new FileInputStream("product.json"));

			List<concordia.comp6841.ecas.entity.Product> products = mapper.readValue(inputStream, typeReference);
			productService.save(products);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Boolean syncCategories(String username, String password, String email) {
		HttpHeaders headers = new HttpHeaders();
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("woo-username", username);
		body.add("woo-password", password);
		body.add("woo-email", email);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<?> entity = new HttpEntity<Object>(body, headers);
		try {
			ResponseEntity<String> response = restTemplate.exchange(
					"http://localhost/wordpress-spm-project/index.php/wp-json/campaignit/v1/woo/get/all/categories",
					HttpMethod.POST, entity, String.class);
			BufferedWriter writer = new BufferedWriter(new FileWriter("categories.json"));
			writer.write(response.getBody());
			writer.close();
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<concordia.comp6841.ecas.entity.Category>> typeReference = new TypeReference<List<concordia.comp6841.ecas.entity.Category>>() {
			};
			InputStream inputStream = new DataInputStream(new FileInputStream("categories.json"));

			List<concordia.comp6841.ecas.entity.Category> categories = mapper.readValue(inputStream, typeReference);
			categoryService.save(categories);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Boolean syncCustomers(String username, String password, String email) {
		HttpHeaders headers = new HttpHeaders();
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("woo-username", username);
		body.add("woo-password", password);
		body.add("woo-email", email);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<?> entity = new HttpEntity<Object>(body, headers);
		try {
			ResponseEntity<String> response = restTemplate.exchange(
					"http://localhost/wordpress-spm-project/index.php/wp-json/campaignit/v1/woo/get/all/customers",
					HttpMethod.POST, entity, String.class);
			BufferedWriter writer = new BufferedWriter(new FileWriter("customer.json"));
			writer.write(response.getBody());
			writer.close();
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<concordia.comp6841.ecas.entity.Customer>> typeReference = new TypeReference<List<concordia.comp6841.ecas.entity.Customer>>() {
			};
			InputStream inputStream = new DataInputStream(new FileInputStream("customer.json"));

			List<concordia.comp6841.ecas.entity.Customer> customers = mapper.readValue(inputStream, typeReference);
			customerService.save(customers);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Boolean syncOrders(String username, String password, String email) {
		HttpHeaders headers = new HttpHeaders();
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("woo-username", username);
		body.add("woo-password", password);
		body.add("woo-email", email);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<?> entity = new HttpEntity<Object>(body, headers);
		try {
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

			List<concordia.comp6841.ecas.entity.COrder> orders = mapper.readValue(inputStream, typeReference);
			orderService.save(orders);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public Boolean syncAbandonedCarts(String username, String password, String email) {
		HttpHeaders headers = new HttpHeaders();
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("woo-username", username);
		body.add("woo-password", password);
		body.add("woo-email", email);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<?> entity = new HttpEntity<Object>(body, headers);
		try {
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

			List<concordia.comp6841.ecas.entity.AbandonedCart> abandonedCarts = mapper.readValue(inputStream,
					typeReference);
			abandonedCartService.save(abandonedCarts);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

}
