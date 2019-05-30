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
public class OrderedProduct implements Serializable {
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

    public void constructOrderedProduct(Product product, String productID) {
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

    /*public OrderedProduct constructOrderedProduct(Product product, String productID) {
        OrderedProduct orderedProduct = new OrderedProduct();

        orderedProduct.setProductID (Integer.parseInt(productID));
        orderedProduct.setAmount    (1);
        orderedProduct.setBrand     (product.getBrand());
        orderedProduct.setModel     (product.getModel());
        orderedProduct.setType      (product.getProductParams().getType());
        orderedProduct.setPic       (product.getProductParams().getPic());
        orderedProduct.setPrice     (product.getPrice());
        orderedProduct.setTotalPrice(product.getPrice());

        return orderedProduct;
    }*/


//@Column(fullName = "product_id")
//@OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)

    /*@OneToOne(optional = false, mappedBy = "orderedProduct", fetch = FetchType.EAGER)
    @JsonIgnore
    private Product productID;*/
    /*@OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(fullName = "product_id")
    private Product product;*/

