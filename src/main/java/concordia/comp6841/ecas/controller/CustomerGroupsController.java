package concordia.comp6841.ecas.controller;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import concordia.comp6841.ecas.entity.CustomerGroup;
import concordia.comp6841.ecas.entity.User;
import concordia.comp6841.ecas.repository.CustomerRepository;
import concordia.comp6841.ecas.service.CustomerGroupService;
import concordia.comp6841.ecas.service.UserService;


@Controller
@RequestMapping("/customer_group")
public class CustomerGroupsController {

	@Autowired
	private CustomerGroupService customerGroupService;

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
    private UserService userService;

	@GetMapping("/all")
	public String showCustomerGroupsForm(Model model, Principal principal) {
		User user = userService.findByEmail(principal.getName());
		model.addAttribute("user_firstName", user.getFirstName());
		model.addAttribute("user_lastName", user.getLastName());
		model.addAttribute("user_email", user.getEmail());
		model.addAttribute("customers", customerRepository.findAll());
		return "customer_group";
	}

	@PostMapping
	public String saveCustomerGroupSettings(@ModelAttribute("customer_group") CustomerGroup customerGroup,
			BindingResult result) {

		customerGroupService.save(customerGroup);
		return "redirect:/customer_group/all?success";
	}

	

}
