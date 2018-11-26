package concordia.comp6841.ecas.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import concordia.comp6841.ecas.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	@Override
	@Transactional()
	Iterable<Customer> findAll();

	//Active Customers
	@Query("select a from Customer a where a.last_seen >= :activeLastSeenTime")
	List<Customer> findAllWithLastSeenBefore(@Param("activeLastSeenTime") Date activeLastSeenTime);
	
	//Inactive Customers
	@Query("select a from Customer a where a.last_seen < :inactiveLastSeenTime")
	List<Customer> findAllWithLastSeenAfter(@Param("inactiveLastSeenTime") Date inactiveLastSeenTime);
}
