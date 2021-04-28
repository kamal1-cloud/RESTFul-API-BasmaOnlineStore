package ma.youcode.ordermicroservice.Repositories;

import ma.youcode.ordermicroservice.Models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {
}
