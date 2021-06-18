package ma.youcode.ordermicroservice.Services;


import lombok.extern.slf4j.Slf4j;
import ma.youcode.ordermicroservice.Models.Cart;
import ma.youcode.ordermicroservice.Repositories.CartRepository;
import ma.youcode.ordermicroservice.VO.Product;
import ma.youcode.ordermicroservice.VO.ResponseTemplateVOCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Cart> listAll() {
        return cartRepository.findAll();
    }

    public Cart AddToCart(Cart cart){
        cartRepository.save(cart);
        return cart;
    }
    public Cart getById(Long id){
        return cartRepository.findById(id).get();
    }
    public void delete(Long id){
        cartRepository.deleteById(id);
    }



    public ResponseTemplateVOCart getCartWithProduct(Long cartId) {
        log.info("Inside getCartWithProduct of ProductService");
        ResponseTemplateVOCart voc = new ResponseTemplateVOCart();
        Cart cart = cartRepository.findById(cartId).get();
        System.out.println("cart is here"+  cart.getProductId());
        Product product =
                restTemplate.getForObject("http://MICROSERVICE-PRODUITS/product/" + cart.getProductId(),
                        Product.class);
        voc.setCart(cart);

        voc.setProduct(product);
        System.out.println("prod"+product.getName());
        return voc;
    }
}
