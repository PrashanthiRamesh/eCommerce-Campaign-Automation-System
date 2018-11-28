package concordia.comp6841.ecas.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import concordia.comp6841.ecas.entity.Campaign;

@Repository
public interface CampaignRepository extends CrudRepository<Campaign, Long> {

	@Override
	@Transactional
	List<Campaign> findAll();

	// Existing Campaign 
	@Query("select a from Campaign a where a.name = :campaignName")
	Campaign findByName(@Param("campaignName") String name);
	
	@Query("select a from Campaign a where a.id = :campaignId")
	Campaign findByid(@Param("campaignId") Long id);

}
