package concordia.comp6841.ecas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import concordia.comp6841.ecas.entity.Customer;
import concordia.comp6841.ecas.repository.CustomerRepository;


@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}
	
	public Iterable<Customer> list(){
		return customerRepository.findAll();
	}
	
	public Iterable<Customer> save(List<Customer> customers){
		return customerRepository.saveAll(customers);
	}
}
