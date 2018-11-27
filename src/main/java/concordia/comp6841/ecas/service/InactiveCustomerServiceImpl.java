package concordia.comp6841.ecas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import concordia.comp6841.ecas.entity.InactiveCustomer;
import concordia.comp6841.ecas.repository.InactiveCustomerRepository;

@Service
public class InactiveCustomerServiceImpl implements InactiveCustomerService {

	private InactiveCustomerRepository inactiveCustomerRepository;

	public InactiveCustomerServiceImpl(InactiveCustomerRepository inactiveCustomerRepository) {
		this.inactiveCustomerRepository = inactiveCustomerRepository;
	}

	public Iterable<InactiveCustomer> list() {
		return inactiveCustomerRepository.findAll();
	}

	public Iterable<InactiveCustomer> save(List<InactiveCustomer> inactiveCustomers) {
		return inactiveCustomerRepository.saveAll(inactiveCustomers);
	}
}
