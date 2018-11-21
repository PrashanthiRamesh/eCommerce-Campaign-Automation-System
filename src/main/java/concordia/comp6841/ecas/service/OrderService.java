package concordia.comp6841.ecas.service;

import java.util.List;

import concordia.comp6841.ecas.entity.COrder;



public interface OrderService {

	Iterable<COrder> save(List<COrder> orders);
}
