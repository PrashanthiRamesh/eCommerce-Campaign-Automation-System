package concordia.comp6841.ecas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import concordia.comp6841.ecas.repository.InactiveCustomerRepository;


@Controller
@RequestMapping("/inactive_customer")
public class InactiveCustomersController {

	@Autowired
	private InactiveCustomerRepository inactiveCustomerRepository;

	@GetMapping("/all")
	public String showAbandonedCartForm(Model model) {
		model.addAttribute("inactive_customers", inactiveCustomerRepository.findAll());
		return "inactive_customer";
	}
}

