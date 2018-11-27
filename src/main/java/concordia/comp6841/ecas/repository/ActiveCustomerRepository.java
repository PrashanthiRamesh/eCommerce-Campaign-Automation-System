package concordia.comp6841.ecas.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import concordia.comp6841.ecas.entity.ActiveCustomer;

@Repository
public interface ActiveCustomerRepository extends CrudRepository<ActiveCustomer, Long> {

	@Override
    @Transactional
    Iterable<ActiveCustomer> findAll();
}
