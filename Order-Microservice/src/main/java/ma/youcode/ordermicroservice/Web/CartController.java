package ma.youcode.ordermicroservice.Web;

import ma.youcode.ordermicroservice.Beans.ProductBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CartController {

    @Autowired
    RestTemplate restTemplate;
    private String BASE_URI = "http://localhost:8082/microservice-produits";

    @GetMapping("/add-one-to-cart/{productId}")
    public ProductBean addOneToCart(@RequestParam("productId") Long productId,
                                    @RequestParam("quantity") Integer quantity){
        String url = BASE_URI+"/"
    }
}
