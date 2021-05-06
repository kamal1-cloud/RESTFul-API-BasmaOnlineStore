//package ma.youcode.controllers;
//
//import java.util.Arrays;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.client.RestTemplate;
//
//public class OrderController {
//
//	@Autowired
//	RestTemplate restTemplate;
//
//	@RequestMapping(value = "/createOrder")
//	public String getProductList() {
//		HttpHeaders headers = new HttpHeaders();
//		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//		HttpEntity<String> entity = new HttpEntity<String>(headers);
//
//		return restTemplate.exchange("http://localhost:8082/microservice-produits", HttpMethod.POST, entity, String.class).getBody();
//	}
//}
