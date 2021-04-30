package ma.youcode.ordermicroservice.Web;

import ma.youcode.ordermicroservice.Beans.ProductBean;
import ma.youcode.ordermicroservice.Models.Cart;
import ma.youcode.ordermicroservice.Models.Orders;
import ma.youcode.ordermicroservice.Services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class CartController {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CartService cartService;
    private String BASE_URI = "http://localhost:8082/microservice-produits";
//
//    @GetMapping("/add-one-to-cart/{productId}")
//    public ProductBean addOneToCart(@RequestParam("productId") Long productId,
//                                    @RequestParam("quantity") Integer quantity){
////        String url = BASE_URI+"/"
//    }
    @GetMapping
    public List<Cart> listOrders(){
        return cartService.listAll();
    }
        // Insert Order record
        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public Cart AddToCart(@RequestBody Cart cart) {

            return cartService.save(cart);
        }

//    Find Order by id

    @GetMapping("/{id}")
    public ResponseEntity<Cart> get(@PathVariable Long id) {
        try {
            Cart cart = cartService.getById(id);
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update Order record
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Cart cart, @PathVariable Long id) {
        try {
            Cart existCart = cartService.getById(id);
            cartService.save(cart);
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
}
