package ma.youcode.store.Repository;

import ma.youcode.store.Model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Products,Long> {
}
