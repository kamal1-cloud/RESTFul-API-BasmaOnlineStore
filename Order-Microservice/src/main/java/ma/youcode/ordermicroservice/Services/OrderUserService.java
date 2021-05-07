package ma.youcode.ordermicroservice.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ma.youcode.VO.ResponseTemVO;
import ma.youcode.VO.User;
import ma.youcode.ordermicroservice.Models.Orders;
import ma.youcode.ordermicroservice.Repositories.OrderRepository;

@Service
public class OrderUserService {
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private RestTemplate restTamplate;

	public ResponseTemVO getOrderWithUser(Long idOrder) {
//		com.sun.tools.sjavac.Log.info("Inside getUserWithOrder of UserOrderService");
		ResponseTemVO vo = new ResponseTemVO();

		Orders order = orderRepository.findById(idOrder).get();

		User user = restTamplate.getForObject("http://USER-MICROSERVICE/api/test/" + order.getUserId(), User.class);

		vo.setOder(order);
		vo.setUser(user);
		return vo;

	}
}
