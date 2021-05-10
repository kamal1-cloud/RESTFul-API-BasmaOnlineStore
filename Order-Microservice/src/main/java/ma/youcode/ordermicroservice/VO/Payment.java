package ma.youcode.ordermicroservice.VO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    private Long paymentId;
    private String paymentStatus;
    private String transactionId;
    private int orderId;
    private int amount;
}
