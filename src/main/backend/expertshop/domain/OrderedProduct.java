package expertshop.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = "orders")
@ToString(exclude = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ordered_product")
public class OrderedProduct implements Serializable {//////////////extend Product
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "product_id")
    private Integer productID;

    @Column(name = "amount")
    private Integer amount;

    private Integer price;

    @Column(name = "total_price")
    private Integer totalPrice;

    private String brand, model, type, pic;

    @JsonIgnore
    @ManyToMany(mappedBy = "orderedProducts")
    private Set<Order> orders;

    public void constructOrderedProduct(Product product, String productID) {/// constructor
        this.setProductID (Integer.parseInt(productID));
        this.setAmount    (1);
        this.setBrand     (product.getBrand());
        this.setModel     (product.getModel());
        this.setType      (product.getProductParams().getType());
        this.setPic       (product.getProductParams().getPic());
        this.setPrice     (product.getPrice());
        this.setTotalPrice(product.getPrice());
    }
}

