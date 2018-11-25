package concordia.comp6841.ecas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import concordia.comp6841.ecas.repository.ProductRepository;

@Controller
@RequestMapping("/product")
public class ProductsController {


	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/all")
	public String showProductsForm(Model model) {
		model.addAttribute("products", productRepository.findAll());
		return "product";
	}
}
