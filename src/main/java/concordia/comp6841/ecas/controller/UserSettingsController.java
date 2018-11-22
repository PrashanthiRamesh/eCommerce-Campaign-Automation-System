package concordia.comp6841.ecas.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import concordia.comp6841.ecas.entity.User;
import concordia.comp6841.ecas.service.UserService;

@Controller
@RequestMapping("/settings")
public class UserSettingsController {

	@Autowired
    private UserService userService;
	
	@GetMapping
    public String showSettingsForm(Model model, Principal principal) {
		User user = userService.findByEmail(principal.getName());
		model.addAttribute("firstName", user.getFirstName());
		model.addAttribute("lastName", user.getLastName());
		model.addAttribute("email", user.getEmail());
        return "settings";
    }

}
