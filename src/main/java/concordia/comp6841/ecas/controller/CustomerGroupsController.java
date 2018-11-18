package concordia.comp6841.ecas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer_group")
public class CustomerGroupsController {
	
	@GetMapping("/all")
    public String showCustomerGroupsForm(Model model) {
        return "customer_group";
    }

}
