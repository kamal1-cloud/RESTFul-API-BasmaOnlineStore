package ma.youcode.ordermicroservice.Web;

import ma.youcode.ordermicroservice.Models.Orders;
import ma.youcode.ordermicroservice.Services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrdersService ordersService;
    @GetMapping
    public List<Orders> listOrders(){
      return ordersService.listOrders();
    }
    // Insert Order record
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Orders newProduct(@RequestBody Orders order) {

        return ordersService.saveOrder(order);
    }

//    Find Order by id

    @GetMapping("/{id}")
    public ResponseEntity<Orders> get(@PathVariable Long id) {
        try {
            Orders order = ordersService.getOrderByid(id);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update Order record
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Orders order, @PathVariable Long id) {
        try {
            Orders existOrders = ordersService.getOrderByid(id);
            ordersService.saveOrder(order);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Delete Product
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id){
        try {
            ordersService.deleteOrder(id);
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch(RuntimeException ex){
            // log the error message
            System.out.println(ex.getMessage());
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

}
