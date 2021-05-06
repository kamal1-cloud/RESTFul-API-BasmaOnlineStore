package ma.youcode.ordermicroservice.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idOrder;
    private int cartId;
    private int userId;
    private double cartItemTotal;
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    private String promoCode;



}
