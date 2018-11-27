package concordia.comp6841.ecas.controller;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import concordia.comp6841.ecas.entity.Customer;
import concordia.comp6841.ecas.entity.CustomerGroup;
import concordia.comp6841.ecas.entity.User;
import concordia.comp6841.ecas.repository.CustomerRepository;
import concordia.comp6841.ecas.service.ActiveCustomerService;
import concordia.comp6841.ecas.service.CustomerGroupService;
import concordia.comp6841.ecas.service.InactiveCustomerService;
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

	@Autowired
	private ActiveCustomerService activeCustomerService;

	@Autowired
	private InactiveCustomerService inactiveCustomerService;

	@GetMapping("/all")
	public String showCustomerGroupsForm(Model model, Principal principal) {
		User user = userService.findByEmail(principal.getName());
		model.addAttribute("email", user.getEmail());
		model.addAttribute("customers", customerRepository.findAll());
		CustomerGroup existing = customerGroupService.findByEmail(user.getEmail());
		if (existing != null) {
			model.addAttribute("customer_group", existing);
		} else {
			CustomerGroup newcs = new CustomerGroup(user.getEmail(), 0, 0);
			CustomerGroup newcustomergroup = customerGroupService.save(newcs);
			model.addAttribute("customer_group", newcustomergroup);
		}

		return "customer_group";
	}

	@PostMapping
	public String saveCustomerGroupSettings(@ModelAttribute("customer_group") CustomerGroup customerGroup,
			BindingResult result) throws ParseException, IOException {
		CustomerGroup existing = customerGroupService.findByEmail(customerGroup.getEmail());
		if (existing != null) {
			// update
			existing.setActive_lastseen(customerGroup.getActive_lastseen());
			existing.setInactive_lastseen(customerGroup.getInactive_lastseen());
			existing = customerGroupService.save(existing);
		} else {
			existing = customerGroupService.save(customerGroup);
		}

		DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

		// create active customers

		Integer active_lastseen = existing.getActive_lastseen();
		DateTime active_date = new DateTime().minusDays(active_lastseen);
		String active_date_str = dateTimeFormatter.print(active_date);
		List<Customer> active_customers = customerRepository
				.findAllWithLastSeenBefore(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(active_date_str));
		ObjectMapper active_mapper = new ObjectMapper();
		BufferedWriter active_writer = new BufferedWriter(new FileWriter("active_customers.json"));
		active_writer.write(active_mapper.writeValueAsString(active_customers));
		active_writer.close();

		TypeReference<List<concordia.comp6841.ecas.entity.ActiveCustomer>> active_typeReference = new TypeReference<List<concordia.comp6841.ecas.entity.ActiveCustomer>>() {
		};
		InputStream active_inputStream = new DataInputStream(new FileInputStream("active_customers.json"));

		List<concordia.comp6841.ecas.entity.ActiveCustomer> activeCustomers = active_mapper
				.readValue(active_inputStream, active_typeReference);

		activeCustomerService.save(activeCustomers);
		// create inactive customers

		Integer inactive_lastseen = existing.getInactive_lastseen();
		DateTime inactive_date = new DateTime().minusDays(inactive_lastseen);
		String inactive_date_str = dateTimeFormatter.print(inactive_date);
		List<Customer> inactive_customers = customerRepository
				.findAllWithLastSeenAfter(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(inactive_date_str));

		ObjectMapper inactive_mapper = new ObjectMapper();
		BufferedWriter inactive_writer = new BufferedWriter(new FileWriter("inactive_customers.json"));
		inactive_writer.write(inactive_mapper.writeValueAsString(inactive_customers));
		inactive_writer.close();
		TypeReference<List<concordia.comp6841.ecas.entity.InactiveCustomer>> inactive_typeReference = new TypeReference<List<concordia.comp6841.ecas.entity.InactiveCustomer>>() {
		};
		InputStream inactive_inputStream = new DataInputStream(new FileInputStream("inactive_customers.json"));

		List<concordia.comp6841.ecas.entity.InactiveCustomer> inActiveCustomers = inactive_mapper
				.readValue(inactive_inputStream, inactive_typeReference);
		inactiveCustomerService.save(inActiveCustomers);

		return "redirect:/customer_group/all?success";
	}

}
