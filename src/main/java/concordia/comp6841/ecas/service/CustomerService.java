package concordia.comp6841.ecas.service;

import java.util.List;

import concordia.comp6841.ecas.entity.Customer;


public interface CustomerService {
	Iterable<Customer> save(List<Customer> customers);
	
}
