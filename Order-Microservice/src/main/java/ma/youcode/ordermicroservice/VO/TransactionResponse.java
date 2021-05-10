package ma.youcode.ordermicroservice.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.ordermicroservice.Models.Orders;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    private Orders order;
    private double amount;
    private String transactionId;
    private String message;
}
