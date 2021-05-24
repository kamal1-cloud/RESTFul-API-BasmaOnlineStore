package ma.youcode.ordermicroservice.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ma.youcode.VO.Products;
import ma.youcode.VO.ResponseTempleteProduct;
import ma.youcode.ordermicroservice.Models.Orders;
import ma.youcode.ordermicroservice.Repositories.OrderRepository;

@Service
public class OrderProduct {
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private RestTemplate restTamplate;

	public ResponseTempleteProduct getOrderWithProduct(Long idOrder) {
//		com.sun.tools.sjavac.Log.info("Inside getUserWithOrder of UserOrderService");
		ResponseTempleteProduct vo = new ResponseTempleteProduct();

		Orders order = orderRepository.findById(idOrder).get();

		Products product = restTamplate.getForObject("http://microservice-produits/product/" + order.getProductId(),Products.class);

		vo.setOder(order);
		vo.setProduct(product);
		return vo;
		

	}
}
