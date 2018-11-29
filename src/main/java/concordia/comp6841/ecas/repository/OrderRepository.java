package concordia.comp6841.ecas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import concordia.comp6841.ecas.entity.COrder;


@Repository
public interface OrderRepository extends JpaRepository<COrder, Long> {

	// Active Customers
	@Query("select a from COrder a where a.customer_email= :customerEmail")
	List<COrder> findByCEmail(@Param("customerEmail") String customerEmail);

}
