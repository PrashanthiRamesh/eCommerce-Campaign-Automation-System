package concordia.comp6841.ecas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import concordia.comp6841.ecas.entity.COrder;
import concordia.comp6841.ecas.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{

	private OrderRepository orderRepository;

	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	public Iterable<COrder> list() {
		return orderRepository.findAll();
	}

	public Iterable<COrder> save(List<COrder> orders) {
		return orderRepository.saveAll(orders);
	}
	
	
}
