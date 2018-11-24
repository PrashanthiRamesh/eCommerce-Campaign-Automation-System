package concordia.comp6841.ecas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import concordia.comp6841.ecas.repository.AbandonedCartRepository;

@Controller
@RequestMapping("/abandoned_cart")
public class AbandonedCartController {

	@Autowired
	private AbandonedCartRepository abandonedCartRepository;

	@GetMapping("/all")
	public String showAbandonedCartForm(Model model) {
		model.addAttribute("abandoned_carts", abandonedCartRepository.findAll());
		return "abandoned_cart";
	}
}
