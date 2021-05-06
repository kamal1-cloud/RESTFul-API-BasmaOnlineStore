package com.basma.PaymentMicroservice.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Long idOrder;
    private int cartId;
    private int userId;
    private double cartItemTotal;
    private Date orderDate;
    private String promoCode;
    
}
