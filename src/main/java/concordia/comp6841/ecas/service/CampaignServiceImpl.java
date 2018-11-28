package concordia.comp6841.ecas.service;

import org.springframework.stereotype.Service;

import concordia.comp6841.ecas.entity.Campaign;
import concordia.comp6841.ecas.repository.CampaignRepository;

@Service
public class CampaignServiceImpl implements  CampaignService{
	private CampaignRepository campaignRepository;

	public CampaignServiceImpl(CampaignRepository campaignRepository) {

		this.campaignRepository = campaignRepository;
	}

	public Iterable<Campaign> list() {
		return campaignRepository.findAll();
	}

	public Campaign save(Campaign campaign) {
		return campaignRepository.save(campaign);
	}
	

	
}
