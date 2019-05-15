package expertshop.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Data
@EqualsAndHashCode(exclude = "orderedProducts")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ordr")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Integer orderID;

    @Column(name = "session_uuid")
    private String sessionUUID;

    @Column(name = "user_id")
    private Integer userID;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "products_to_order",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "ordered_product"))
    private Set<OrderedProduct> orderedProducts;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "completed")
    private Boolean completed;

    public void addProductToOrder(OrderedProduct orderedProduct) {
        if (this.getOrderedProducts() == null) {
            this.orderedProducts = new HashSet<>();
            orderedProducts.add(orderedProduct);
        }
        else this.orderedProducts.add(orderedProduct);
    }

    public void removeProductFromOrder(OrderedProduct orderedProduct) {
        orderedProducts.remove(orderedProduct);
    }
}
