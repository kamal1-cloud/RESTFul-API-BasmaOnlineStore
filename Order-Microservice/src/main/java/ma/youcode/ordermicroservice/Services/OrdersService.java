package ma.youcode.ordermicroservice.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ma.youcode.ordermicroservice.Models.Cart;
import ma.youcode.ordermicroservice.Models.Orders;
import ma.youcode.ordermicroservice.Repositories.OrderRepository;
import ma.youcode.ordermicroservice.VO.ResponceTemplateVOrder;

@Service
public class OrdersService {
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	private RestTemplate restTemplate;

	public List<Orders> listOrders() {
		return orderRepository.findAll();
	}

	public Orders saveOrder(Orders order) {
		orderRepository.save(order);
		return order;
	}

	public Orders getOrderByid(Long id) {
		return orderRepository.findById(id).get();
	}

	public void deleteOrder(Long id) {
		orderRepository.deleteById(id);
	}

	public ResponceTemplateVOrder getOrderWithCart(Long orderId) {

//        log.info("Inside getOrderWithCart of OrderService");
		ResponceTemplateVOrder vo = new ResponceTemplateVOrder();
		Orders order = orderRepository.findById(orderId).get();
		Cart cart = restTemplate.getForObject("http://MICROSERVICE-COMMANDE/cart/" + order.getCartId(), Cart.class);
		vo.setOrder(order);
		vo.setCart(cart);
		return vo;
	}
}
