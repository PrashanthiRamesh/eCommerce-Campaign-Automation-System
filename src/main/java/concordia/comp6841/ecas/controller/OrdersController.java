package concordia.comp6841.ecas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import concordia.comp6841.ecas.repository.OrderRepository;
import concordia.comp6841.ecas.repository.ProductRepository;

@Controller
@RequestMapping("/order")
public class OrdersController {


	@Autowired
	private OrderRepository orderRepository;

	@GetMapping("/all")
	public String showOrdersForm(Model model) {
		model.addAttribute("orders", orderRepository.findAll());
		return "order";
	}
}