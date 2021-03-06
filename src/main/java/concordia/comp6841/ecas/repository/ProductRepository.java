package concordia.comp6841.ecas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import concordia.comp6841.ecas.entity.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


}
