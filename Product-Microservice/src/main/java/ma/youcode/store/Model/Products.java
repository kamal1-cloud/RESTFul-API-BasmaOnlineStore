package ma.youcode.store.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.InstanceAlreadyExistsException;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Products")
@Data
@NoArgsConstructor
//@AllArgsConstructor
public class Products {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long productId;

    @Column
    private String name;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean status;

    @Column(nullable = false)
    private String mainImage;


    private int CategoryId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId", referencedColumnName = "productId")
    List<Images> images = new ArrayList<>();

    public Products(String name, double price, int quantity, String description, boolean status, String mainImage, int categoryId) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.status = status;
        this.mainImage = mainImage;
        CategoryId = categoryId;

    }
}
