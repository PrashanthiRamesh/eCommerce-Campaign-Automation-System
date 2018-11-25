package concordia.comp6841.ecas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import concordia.comp6841.ecas.entity.CustomerGroup;


@Repository
public interface CustomerGroupRepository extends CrudRepository<CustomerGroup, Long> {

}
