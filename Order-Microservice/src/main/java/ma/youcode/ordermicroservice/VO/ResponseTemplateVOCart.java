package ma.youcode.ordermicroservice.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.ordermicroservice.Models.Cart;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVOCart {
    private Cart cart;
    private Product product;

}
