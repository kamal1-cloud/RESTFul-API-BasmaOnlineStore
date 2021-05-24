package ma.youcode.ordermicroservice.Web;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import ma.youcode.VO.ResponseTemVO;
import ma.youcode.VO.ResponseTempleteProduct;
import ma.youcode.ordermicroservice.Models.Orders;
import ma.youcode.ordermicroservice.Services.OrderProduct;
import ma.youcode.ordermicroservice.Services.OrderUserService;
import ma.youcode.ordermicroservice.Services.OrdersService;

@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {
	@Autowired
	OrdersService ordersService;
	@Autowired
	OrderUserService ordersUserService;

	@Autowired
	OrderProduct orderProduct;

	@GetMapping
	public List<Orders> listOrders() {
		return ordersService.listOrders();
	}

	// Insert Order record
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Orders newProduct(@RequestBody Orders order) {

		return ordersService.saveOrder(order);
	}

//    Find Order by id
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Orders> get(@PathVariable Long id) {
//        try {
//            Orders order = ordersService.getOrderByid(id);
//            return new ResponseEntity<>(order, HttpStatus.OK);
//        } catch (NoSuchElementException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

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
	public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
		try {
			ordersService.deleteOrder(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (RuntimeException ex) {
			// log the error message
			System.out.println(ex.getMessage());
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}

//    @GetMapping("/{id}")
//    public ResponceTemplateVOrder getOrderWithCart(@PathVariable("id") Long orderId){
////        log.info("getProductWithCatgory of CartController");
//        return ordersService.getOrderWithCart(orderId);
//    }

	@GetMapping("/{id}")
	public ResponseTemVO getOrderWithUser(@PathVariable("id") Long orderId) {
//        log.info("getProductWithCatgory of CartController");
		return ordersUserService.getOrderWithUser(orderId);
	}

	@GetMapping("/{id}")
	public ResponseTempleteProduct getOrderWithProduct(@PathVariable("id") Long orderId) {
//        log.info("getProductWithCatgory of CartController");
		return orderProduct.getOrderWithProduct(orderId);

	}
}
