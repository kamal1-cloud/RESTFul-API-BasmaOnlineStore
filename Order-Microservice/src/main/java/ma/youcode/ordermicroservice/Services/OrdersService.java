package ma.youcode.ordermicroservice.Services;

import ma.youcode.ordermicroservice.Models.Orders;
import ma.youcode.ordermicroservice.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {
  @Autowired
  OrderRepository orderRepository;

   public List<Orders> listOrders(){
  return orderRepository.findAll();
   }
   public Orders saveOrder(Orders order){
     orderRepository.save(order);
     return order;
   }
   public Orders getOrderByid(Long id){
   return orderRepository.findById(id).get();
   }
   public void deleteOrder(Long id){
     orderRepository.deleteById(id);
   }
}
