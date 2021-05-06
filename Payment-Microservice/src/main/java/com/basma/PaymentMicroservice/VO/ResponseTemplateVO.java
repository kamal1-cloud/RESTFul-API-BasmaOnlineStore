package com.basma.PaymentMicroservice.VO;

import com.basma.PaymentMicroservice.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {
    private Payment payment;
    private Order order;
}
