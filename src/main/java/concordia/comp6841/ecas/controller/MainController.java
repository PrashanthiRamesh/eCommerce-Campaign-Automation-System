package concordia.comp6841.ecas.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import concordia.comp6841.ecas.dto.UserRegisterDto;
import concordia.comp6841.ecas.entity.User;
import concordia.comp6841.ecas.service.UserService;

@Controller
public class MainController {

	@GetMapping("/")
	public String homeForm(Model model) {
		return "home";
	}

	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password are invalid.");

		if (logout != null)
			model.addAttribute("logout", "You have been logged out successfully.");

		return "login";
	}
	
	/* Test */

}
