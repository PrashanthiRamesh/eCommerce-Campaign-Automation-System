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
		model.addAttribute("email", user.getEmail());
		model.addAttribute("customers", customerRepository.findAll());
		CustomerGroup existing = customerGroupService.findByEmail(user.getEmail());
		if (existing != null) {
			model.addAttribute("customer_group",existing);	
		}else {
			CustomerGroup newcs=new CustomerGroup(user.getEmail(), 0, 0);
			CustomerGroup newcustomergroup= customerGroupService.save(newcs);
			model.addAttribute("customer_group", newcustomergroup);
		}

		return "customer_group";
	}

	@PostMapping
	public String saveCustomerGroupSettings(@ModelAttribute("customer_group") CustomerGroup customerGroup,
			BindingResult result) {
		CustomerGroup existing = customerGroupService.findByEmail(customerGroup.getEmail());
		if (existing != null) {
			//update
			existing.setActive_lastseen(customerGroup.getActive_lastseen());
			existing.setInactive_lastseen(customerGroup.getInactive_lastseen());
			customerGroupService.save(existing);
		} else {
			customerGroupService.save(customerGroup);
		}
		
		//create active customers
		
		//create inactive customers

		return "redirect:/customer_group/all?success";
	}

}
