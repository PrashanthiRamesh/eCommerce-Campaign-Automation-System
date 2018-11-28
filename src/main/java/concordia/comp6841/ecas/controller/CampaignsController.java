package concordia.comp6841.ecas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import concordia.comp6841.ecas.entity.Campaign;
import concordia.comp6841.ecas.repository.CampaignRepository;
import concordia.comp6841.ecas.service.CampaignService;

@Controller
@RequestMapping("/campaign")
public class CampaignsController {

	@Autowired
	CampaignRepository campaignRepository;

	@Autowired
	CampaignService campaignService;

	@GetMapping("/all")
	public String showCampaignsForm(Model model) {
		model.addAttribute("campaigns", campaignRepository.findAll());
		return "campaign";
	}

	@GetMapping
	public String showCreateCampaignForm(Model model) {
		return "campaign_save";
	}

	@PostMapping
	public String SaveCampaignForm(@ModelAttribute("campaign") Campaign campaign, BindingResult result) {
		Campaign existing = campaignRepository.findByName(campaign.getName());
		if (existing != null) {
			return "redirect:/campaign?fail";
		} else {
			// save campaign

			campaignService.save(campaign);
			return "redirect:/campaign?success";
		}

	}

	@GetMapping("/{id}")
	public String showEditCampaignForm(Model model, @PathVariable Long id) {
		model.addAttribute("campaign", campaignRepository.findByid(id));
		return "campaign_edit";
	}

	@PostMapping("/{id}")
	public String updateCampaignForm(@ModelAttribute("campaign") Campaign campaign, BindingResult result, Model model,
			@PathVariable Long id) {
		Campaign existing = campaignRepository.findByid(id);
		if (existing != null) {

			// save campaign
			campaignService.save(campaign);
			return "redirect:/campaign/{id}?success";
		} else {
			return "redirect:/campaign/{id}?fail";
		}

	}

	@GetMapping("/delete/{id}")
	public String showDeleteCampaignForm(Model model, @PathVariable Long id) {
		Campaign existing = campaignRepository.findByid(id);
		if (existing != null) {

			// save campaign
			campaignRepository.delete(existing);
			return "redirect:/campaign/all?delete_success";
		} else {
			return "redirect:/campaign/all?fail";
		}

	}
}
