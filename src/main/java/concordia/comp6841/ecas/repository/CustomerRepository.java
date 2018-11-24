package concordia.comp6841.ecas.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import concordia.comp6841.ecas.entity.Customer;


@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	@Override
    @Transactional()
    Iterable<Customer> findAll();
}
