package concordia.comp6841.ecas.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import concordia.comp6841.ecas.entity.InactiveCustomer;



@Repository
public interface InactiveCustomerRepository extends CrudRepository<InactiveCustomer, Long> {

	@Override
    @Transactional
    List<InactiveCustomer> findAll();
}

