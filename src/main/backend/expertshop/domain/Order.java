package expertshop.domain;

import expertshop.repos.ProductRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prod_order")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Integer orderID;

    @Column(name = "session_uuid")
    private String sessionUUID;

    @Column(name = "user_id")
    private Integer userID;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "products_to_order",
            joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "count")
    private Map<Integer, Integer> orderedProducts;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "completed")
    private Boolean completed;

    public void addProductToOrder(Integer productID, Integer amount)
    {
        if (this.getOrderedProducts() == null) {
            this.orderedProducts = new LinkedHashMap<>();
            orderedProducts.put(productID, amount);
        }
        else this.orderedProducts.put(productID, amount);
    }

    public void removeProductFromOrder(Integer productID) {
        orderedProducts.remove(productID);
    }
}

/*private OrderList orderedProducts;*/

    /*@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "products_to_order",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> orderedProducts;*/
   /*

   public void addProductToOrder(Product product)
    {
        if (getOrderedProducts() == null) {
            orderedProducts = new HashSet<>();
            orderedProducts.add(product);
        }
        else orderedProducts.add(product);
    }*/

