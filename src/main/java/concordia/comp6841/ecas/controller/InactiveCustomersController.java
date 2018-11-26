package concordia.comp6841.ecas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/inactive_customer")
public class InactiveCustomersController {

//	@Autowired
//	private AbandonedCartRepository abandonedCartRepository;

	@GetMapping("/all")
	public String showAbandonedCartForm(Model model) {
	//	model.addAttribute("abandoned_carts", abandonedCartRepository.findAll());
		return "inactive_customer";
	}
}

