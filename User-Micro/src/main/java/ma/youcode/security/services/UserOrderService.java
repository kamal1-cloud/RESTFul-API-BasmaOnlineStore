//package ma.youcode.security.services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import lombok.extern.slf4j.Slf4j;
//import ma.youcode.VO.Order;
//import ma.youcode.VO.ResponseTemplateVO;
//import ma.youcode.models.User;
//import ma.youcode.repository.UserRepository;
//
//@Service
//@Slf4j
//public class UserOrderService {
//	@Autowired
//	private UserRepository userRepository;
//	@Autowired
//	private RestTemplate restTamplate;
//
//	public ResponseTemplateVO getUserWithOrder(Long userId) {
////		com.sun.tools.sjavac.Log.info("Inside getUserWithOrder of UserOrderService");
//		ResponseTemplateVO vo = new ResponseTemplateVO();
//		User user = userRepository.findByUserId("userId");
//
//		Order order = restTamplate.getForObject("http://Order-Microservice/order/" + user.getIdOrder(), Order.class);
//
//		vo.setUser(user);
//		vo.setOrder(order);
//
//		return vo;
//	}
//}
