package concordia.comp6841.ecas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import concordia.comp6841.ecas.entity.Campaign;
import concordia.comp6841.ecas.repository.CampaignRepository;

@Service
public class CampaignServiceImpl {
	private CampaignRepository campaignRepository;

	public CampaignServiceImpl(CampaignRepository campaignRepository) {
		super();
		this.campaignRepository = campaignRepository;
	}

	public Iterable<Campaign> list() {
		return campaignRepository.findAll();
	}

	public Iterable<Campaign> save(List<Campaign> campaigns) {
		return campaignRepository.saveAll(campaigns);
	}
}
