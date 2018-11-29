package concordia.comp6841.ecas.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import concordia.comp6841.ecas.repository.AbandonedCartRepository;
import concordia.comp6841.ecas.repository.ActiveCustomerRepository;
import concordia.comp6841.ecas.repository.CampaignRepository;
import concordia.comp6841.ecas.repository.CustomerRepository;
import concordia.comp6841.ecas.repository.InactiveCustomerRepository;
import concordia.comp6841.ecas.repository.OrderRepository;
import concordia.comp6841.ecas.repository.ProductRepository;

@Controller
public class MainController {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CampaignRepository campaignRepository;
	
	@Autowired
	private ActiveCustomerRepository activeCustomerRepository;
	
	@Autowired
	private InactiveCustomerRepository inactiveCustomerRepository;
	
	@Autowired
	private AbandonedCartRepository abandonedCartRepository;
	
	@GetMapping("/")
	public String homeForm(Model model) {
		//send order total, product total, customer total, campaigns total
		model.addAttribute("product_count", productRepository.count());
		model.addAttribute("customer_count", customerRepository.count());
		model.addAttribute("order_count", orderRepository.count());
		model.addAttribute("campaign_count", campaignRepository.count());
		model.addAttribute("active_customer_count", activeCustomerRepository.count());
		model.addAttribute("abandoned_cart_count", abandonedCartRepository.count());
		model.addAttribute("inactive_customer_count", inactiveCustomerRepository.count());
		
		return "home";
	}

	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password are invalid.");

		if (logout != null)
			model.addAttribute("logout", "You have been logged out successfully.");

		return "login";
	}
	
	/* Test */

}
