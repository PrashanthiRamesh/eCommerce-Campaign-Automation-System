package concordia.comp6841.ecas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import concordia.comp6841.ecas.repository.OrderRepository;
import concordia.comp6841.ecas.repository.ProductRepository;

@Controller
@RequestMapping("/order/item")
public class OrderItemsController {


	@Autowired
	private OrderRepository orderRepository;

	@GetMapping("/{id}")
	public String showOrderItemsForm(Model model, @PathVariable Long id) {
		model.addAttribute("order", orderRepository.findById(id).get());
		return "order_item";
	}
	
}