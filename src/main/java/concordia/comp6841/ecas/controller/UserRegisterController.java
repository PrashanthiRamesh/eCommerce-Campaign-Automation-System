package concordia.comp6841.ecas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import concordia.comp6841.ecas.entity.User;
import concordia.comp6841.ecas.service.UserService;
import concordia.comp6841.ecas.dto.UserRegisterDto;;

@Controller
@RequestMapping("/register")
public class UserRegisterController {

	@Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserRegisterDto userRegistrationDto() {
        return new UserRegisterDto();
    }
    
    @GetMapping
    public String showRegistrationForm(Model model) {
        return "register";
    }
    
    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegisterDto userDto, 
                                      BindingResult result){

        User existing = userService.findByEmail(userDto.getEmail());
        if (existing != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()){
            return "register";
        }

        userService.save(userDto);
        return "redirect:/register?success";
    }
}
