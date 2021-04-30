package ma.youcode.ordermicroservice.Services;

import ma.youcode.ordermicroservice.Beans.ProductBean;
import ma.youcode.ordermicroservice.Models.Cart;
import ma.youcode.ordermicroservice.Repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    public List<Cart> listAll() {
        return cartRepository.findAll();
    }
    public Cart save(Cart cart){
        cartRepository.save(cart);
        return cart;
    }
    public Cart getById(Long id){
        return cartRepository.findById(id).get();
    }
    public void delete(Long id){
        cartRepository.deleteById(id);
    }
}
