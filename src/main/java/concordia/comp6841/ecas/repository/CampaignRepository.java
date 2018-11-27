package concordia.comp6841.ecas.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import concordia.comp6841.ecas.entity.Campaign;

@Repository
public interface CampaignRepository extends CrudRepository<Campaign, Long> {

	@Override
    @Transactional
    Iterable<Campaign> findAll();
}
