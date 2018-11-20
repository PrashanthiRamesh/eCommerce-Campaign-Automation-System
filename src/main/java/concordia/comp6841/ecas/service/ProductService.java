package concordia.comp6841.ecas.service;

import java.util.List;

import concordia.comp6841.ecas.entity.Product;


public interface ProductService {
	Iterable<Product> save(List<Product> products);
	
}
