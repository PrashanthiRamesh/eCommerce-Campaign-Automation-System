package concordia.comp6841.ecas.service;

import java.util.List;

import concordia.comp6841.ecas.entity.Category;

public interface CategoryService {
	
	Iterable<Category> save(List<Category> categories);

}
