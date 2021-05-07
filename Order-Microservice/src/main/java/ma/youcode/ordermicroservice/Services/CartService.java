package ma.youcode.ordermicroservice.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ma.youcode.ordermicroservice.Models.Cart;
import ma.youcode.ordermicroservice.Repositories.CartRepository;
import ma.youcode.ordermicroservice.VO.Product;
import ma.youcode.ordermicroservice.VO.ResponseTemplateVO;

@Service
public class CartService {
	@Autowired
	CartRepository cartRepository;

	@Autowired
	private RestTemplate restTemplate;

	public List<Cart> listAll() {
		return cartRepository.findAll();
	}

	public Cart AddToCart(Cart cart) {
		cartRepository.save(cart);
		return cart;
	}

	public Cart getById(Long id) {
		return cartRepository.findById(id).get();
	}

	public void delete(Long id) {
		cartRepository.deleteById(id);
	}

	public ResponseTemplateVO getCartWithProduct(Long cartId) {

		ResponseTemplateVO voc = new ResponseTemplateVO();
		Cart cart = cartRepository.findById(cartId).get();
		Product product = restTemplate.getForObject("http://MICROSERVICE-PRODUITS/product/" + cart.getProductId(),
				Product.class);

		voc.setCart(cart);
		voc.setProduct(product);
		return voc;
	}
}
