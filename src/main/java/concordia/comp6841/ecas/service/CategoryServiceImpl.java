package concordia.comp6841.ecas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import concordia.comp6841.ecas.entity.Category;
import concordia.comp6841.ecas.entity.Product;
import concordia.comp6841.ecas.repository.CategoryRepository;
import concordia.comp6841.ecas.repository.ProductRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}
	
	public Iterable<Category> list() {
		return categoryRepository.findAll();
	}

	public Iterable<Category> save(List<Category> categories) {
		return categoryRepository.saveAll(categories);
	}
	
}

