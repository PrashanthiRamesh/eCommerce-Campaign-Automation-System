package concordia.comp6841.ecas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import concordia.comp6841.ecas.entity.ActiveCustomer;
import concordia.comp6841.ecas.repository.ActiveCustomerRepository;

@Service
public class ActiveCustomerServiceImpl implements ActiveCustomerService {

	private ActiveCustomerRepository activeCustomerRepository;

	public ActiveCustomerServiceImpl(ActiveCustomerRepository activeCustomerRepository) {
		super();
		this.activeCustomerRepository = activeCustomerRepository;
	}

	public Iterable<ActiveCustomer> list() {
		return activeCustomerRepository.findAll();
	}

	public Iterable<ActiveCustomer> save(List<ActiveCustomer> activeCustomers) {
		return activeCustomerRepository.saveAll(activeCustomers);
	}
}
