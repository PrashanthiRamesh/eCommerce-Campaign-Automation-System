package concordia.comp6841.ecas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrdersController {

	@GetMapping("/all")
    public String showOrdersForm(Model model) {
        return "order";
    }
}