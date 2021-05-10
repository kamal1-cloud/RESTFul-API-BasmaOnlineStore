package ma.youcode.ordermicroservice.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.ordermicroservice.Models.Orders;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
    private Orders order;
    private Payment payment;
}
