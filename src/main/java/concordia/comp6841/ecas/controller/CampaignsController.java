package concordia.comp6841.ecas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/campaign")
public class CampaignsController {

	@GetMapping("/all")
    public String showCampaignsForm(Model model) {
        return "campaign";
    }
	
	@GetMapping
    public String showCreateCampaignForm(Model model) {
        return "campaign_create";
    }
	
	@PostMapping
    public String SaveCampaignForm(Model model) {
        return "campaign_create";
    }
}
