package ma.youcode.ordermicroservice.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long productId;
    private String name;
    private double price;
    private String description;
    private int CategoryId;
   // List<Images> images = new ArrayList<>();

}
