package ma.youcode.ordermicroservice.Services;

import lombok.extern.slf4j.Slf4j;
import ma.youcode.ordermicroservice.Models.Cart;
import ma.youcode.ordermicroservice.Models.Orders;
import ma.youcode.ordermicroservice.Repositories.OrderRepository;
import ma.youcode.ordermicroservice.VO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class OrdersService {
  @Autowired
  OrderRepository orderRepository;
    @Autowired
    private RestTemplate restTemplate;

   public List<Orders> listOrders(){
  return orderRepository.findAll();
   }
   public TransactionResponse saveOrder(TransactionRequest request){
       String response="";
       Orders order=request.getOrder();
       Payment payment=request.getPayment();
       payment.setOrderId((int) order.getIdOrder());
       payment.setAmount((int) order.getCartItemTotal());
       Payment paymentResponse = restTemplate.postForObject("http://PAYMENT-SERVICE/payment/newPayment", payment, Payment.class);
       response = paymentResponse.getPaymentStatus().equals("success")?"payment processing successful and order placed":"there is a failure in payment, order added to cart";
       orderRepository.save(order);
        return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(), response);
   }
   public Orders getOrderByid(Long id){
   return orderRepository.findById(id).get();
   }

   public void deleteOrder(Long id){
     orderRepository.deleteById(id);
   }

    public ResponceTemplateVOrder getOrderWithCart(Long orderId) {
        log.info("Inside getOrderWithCart of OrderService");
        ResponceTemplateVOrder vo = new ResponceTemplateVOrder();
        Orders order = orderRepository.findById(orderId).get();
        Cart cart =
                restTemplate.getForObject("http://MICROSERVICE-COMMANDE/cart/" + order.getCartId() ,
                        Cart.class);
        vo.setOrder(order);
        vo.setCart(cart);
        return vo;
    }
}
