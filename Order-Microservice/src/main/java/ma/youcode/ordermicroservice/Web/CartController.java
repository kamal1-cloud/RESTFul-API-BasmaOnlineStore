package ma.youcode.ordermicroservice.Web;


import lombok.extern.slf4j.Slf4j;
import ma.youcode.ordermicroservice.Models.Cart;
import ma.youcode.ordermicroservice.Services.CartService;
import ma.youcode.ordermicroservice.VO.ResponseTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/cart")
@Slf4j
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping
    public List<Cart> listOrders(){
        return cartService.listAll();
    }

        // Insert Order record
        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public Cart AddToCart(@RequestBody Cart cart) {

            return cartService.AddToCart(cart);
        }

////    Find Order by id
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Cart> get(@PathVariable Long id) {
//        try {
//            Cart cart = cartService.getById(id);
//            return new ResponseEntity<>(cart, HttpStatus.OK);
//        } catch (NoSuchElementException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    // Update Order record
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Cart cart, @PathVariable Long id) {
        try {
            Cart existCart = cartService.getById(id);
            cartService.AddToCart(cart);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Delete Product
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id){
        try {
            cartService.delete(id);
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch(RuntimeException ex){
            // log the error message
            System.out.println(ex.getMessage());
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getProductWithCatgory (@PathVariable("id") Long cartId){
//        log.info("getProductWithCatgory of CartController");
        return cartService.getCartWithProduct(cartId);
    }
}
