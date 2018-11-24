package concordia.comp6841.ecas.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import concordia.comp6841.ecas.entity.Customer;
import concordia.comp6841.ecas.repository.CustomerRepository;
import concordia.comp6841.ecas.service.CustomerService;

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
