package ma.youcode.ordermicroservice.Repositories;

import ma.youcode.ordermicroservice.Models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
