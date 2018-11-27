package concordia.comp6841.ecas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import concordia.comp6841.ecas.repository.ActiveCustomerRepository;


@Controller
@RequestMapping("/active_customer")
public class ActiveCustomersController {

	@Autowired
	private ActiveCustomerRepository activeCustomerRepository;

	@GetMapping("/all")
	public String showActiveCustomerForm(Model model) {
		model.addAttribute("active_customers", activeCustomerRepository.findAll());
		return "active_customer";
	}
}
