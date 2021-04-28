package ma.youcode.ordermicroservice.Beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductBean {
    private String name;

    private double price;

    private int quantity;

    private String description;

    private boolean status;

 //   List<Images> images = new ArrayList<>();

}
