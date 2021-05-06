package ma.youcode.VO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private long idOrder;
    private int cartId;
    private double cartItemTotal;
    private Date orderDate;
    private double cartTotal;
    private String promoCode;
    
}
