package expertshop.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.NaturalIdCache;

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
public class OrderedProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ordered_id")
    private Long orderedID;

    public String productID, productName, productType, pic;

    private Integer productPrice, totalPrice, bonus, totalBonus, totalAmount;

    @JsonIgnore
    @ManyToMany(mappedBy = "orderedProducts")
    private Set<Order> orders;

    public void constructOrderedProduct(Product product)
    {
        this.setProductID       (product.getProductID());
        this.setTotalAmount     (1);
        this.setProductName     (product.getOriginalName());
        this.setProductType     (product.getSingleType());
        this.setPic             (product.getOriginalPic());
        this.setProductPrice    (product.getFinalPrice());
        this.setTotalPrice      (product.getFinalPrice());
    }
}

