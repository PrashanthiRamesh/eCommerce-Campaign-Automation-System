package concordia.comp6841.ecas.service;

import java.util.List;

import concordia.comp6841.ecas.entity.ActiveCustomer;

public interface ActiveCustomerService {
	
	Iterable<ActiveCustomer> save(List<ActiveCustomer> activeCustomers);

}
