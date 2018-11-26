package concordia.comp6841.ecas.service;

import concordia.comp6841.ecas.entity.CustomerGroup;


public interface CustomerGroupService {

	CustomerGroup findByEmail(String email);

	CustomerGroup save(CustomerGroup customerGroups);

 
}
