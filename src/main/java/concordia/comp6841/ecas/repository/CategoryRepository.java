package concordia.comp6841.ecas.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import concordia.comp6841.ecas.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
