package ma.youcode.store.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Products")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    private int CategoryId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId", referencedColumnName = "productId")
    List<Images> images = new ArrayList<>();


}
