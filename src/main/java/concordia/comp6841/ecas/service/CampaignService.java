package concordia.comp6841.ecas.service;

import java.util.List;

import concordia.comp6841.ecas.entity.Campaign;

public interface CampaignService {
	Iterable<Campaign> save(List<Campaign> campaigns);
}
