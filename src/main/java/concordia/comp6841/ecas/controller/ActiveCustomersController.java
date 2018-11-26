package concordia.comp6841.ecas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/active_customer")
public class ActiveCustomersController {

//	@Autowired
//	private AbandonedCartRepository abandonedCartRepository;

	@GetMapping("/all")
	public String showActiveCustomerForm(Model model) {
		//model.addAttribute("abandoned_carts", abandonedCartRepository.findAll());
		return "active_customer";
	}
}
