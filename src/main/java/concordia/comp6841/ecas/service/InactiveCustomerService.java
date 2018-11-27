package concordia.comp6841.ecas.service;

import java.util.List;

import concordia.comp6841.ecas.entity.InactiveCustomer;

public interface InactiveCustomerService {
	
	Iterable<InactiveCustomer> save(List<InactiveCustomer> inactiveCustomers);

}
