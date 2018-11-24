package concordia.comp6841.ecas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import concordia.comp6841.ecas.entity.AbandonedCart;
import concordia.comp6841.ecas.repository.AbandonedCartRepository;

@Service
public class AbandonedCartServiceImpl implements AbandonedCartService {

	private AbandonedCartRepository abandonedCartRepository;

	public AbandonedCartServiceImpl(AbandonedCartRepository abandonedCartRepository) {
		this.abandonedCartRepository = abandonedCartRepository;
	}

	public Iterable<AbandonedCart> list() {
		return abandonedCartRepository.findAll();
	}

	public Iterable<AbandonedCart> save(List<AbandonedCart> abandonedCarts) {
		return abandonedCartRepository.saveAll(abandonedCarts);
	}
}
