package concordia.comp6841.ecas.service;

import java.util.List;

import concordia.comp6841.ecas.entity.AbandonedCart;

public interface AbandonedCartService {

	Iterable<AbandonedCart> save(List<AbandonedCart> abandonedCarts);
}
