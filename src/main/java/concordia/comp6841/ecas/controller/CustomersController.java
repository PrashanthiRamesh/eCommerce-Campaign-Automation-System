package concordia.comp6841.ecas.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import concordia.comp6841.ecas.repository.CustomerRepository;

@Controller
@RequestMapping("/customer")
public class CustomersController {

	@Autowired
	private CustomerRepository customerRepository;

	@GetMapping("/all")
	public String showCustomersForm(Model model) {
		model.addAttribute("customers", customerRepository.findAll());
		return "customer";
	}
}
