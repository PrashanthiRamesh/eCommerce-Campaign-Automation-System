package concordia.comp6841.ecas.service;

import org.springframework.stereotype.Service;
import concordia.comp6841.ecas.entity.CustomerGroup;
import concordia.comp6841.ecas.entity.User;
import concordia.comp6841.ecas.repository.CustomerGroupRepository;

@Service
public class CustomerGroupServiceImpl implements CustomerGroupService {

	private CustomerGroupRepository customerGroupRepository;

	public CustomerGroupServiceImpl(CustomerGroupRepository customerGroupRepository) {
		super();
		this.customerGroupRepository = customerGroupRepository;
	}

	public Iterable<CustomerGroup> list() {
		return customerGroupRepository.findAll();
	}

	public CustomerGroup save(CustomerGroup customerGroup) {
		return customerGroupRepository.save(customerGroup);
	}

	public CustomerGroup findByEmail(String email) {
		return customerGroupRepository.findByEmail(email);
	}

}
