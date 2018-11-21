package concordia.comp6841.ecas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import concordia.comp6841.ecas.entity.Product;

import concordia.comp6841.ecas.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Iterable<Product> list() {
		return productRepository.findAll();
	}

	public Iterable<Product> save(List<Product> products) {
		return productRepository.saveAll(products);
	}

}
