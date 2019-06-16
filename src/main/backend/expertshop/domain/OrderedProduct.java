package expertshop.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
//@EqualsAndHashCode(callSuper = true, exclude = "orders")
@EqualsAndHashCode(exclude = "orders")
@ToString(exclude = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ordered_product")
/*@DiscriminatorValue("PRODUCT")
@SuppressWarnings("PMD")*/
public class OrderedProduct /*extends Product*/implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ordered_id")
    private String orderedID;

    public String productID;

    /*@JoinColumn(name = "product_id")
    private Integer productID;*/
    //private Integer finalPrice;

    @Column(name = "total_amount")
    private Integer totalAmount;

    @Column(name = "total_price")
    private Integer totalPrice;

    @JsonIgnore
    @ManyToMany(mappedBy = "orderedProducts")
    private Set<Order> orders;



    public void constructOrderedProduct(Product product, String productID) {/// constructor
        /*this.setProductID (Integer.parseInt(productID));
        this.setAmount    (1);
        this.setBrand     (product.getBrand());
        this.setModel     (product.getModel());
        this.setType      (product.getProductParams().getType());
        this.setPic       (product.getPic());
        this.setFinalPrice     (product.getFinalPrice());
        this.setTotalPrice(product.getFinalPrice());*/
    }
}

