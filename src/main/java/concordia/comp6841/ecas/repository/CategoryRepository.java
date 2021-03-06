package concordia.comp6841.ecas.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import concordia.comp6841.ecas.entity.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

	
}
