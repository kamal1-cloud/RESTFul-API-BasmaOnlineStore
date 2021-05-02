package ma.youcode.apigateway.FallBacks;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {
    @GetMapping("/productServiceFallBack")
    public String productServiceFallBackMethod() {
        return "Products Service is taking longer than Expected." +
                " Please try again later";
    }

    @GetMapping("/categoryServiceFallBack")
    public String categoryServiceFallBackMethod() {
        return "Category Service is taking longer than Expected." +
                " Please try again later";
    }
}
