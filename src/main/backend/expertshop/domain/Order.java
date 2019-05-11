package expertshop.domain;

import expertshop.repos.ProductRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prod_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Integer orderID;

    @Column(name = "session_uuid")
    private String sessionUUID;

    @Column(name = "user_id")
    private Integer userID;

    //@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    /*@ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "products_to_order",
            joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyColumn(name = "product_id")
    @Column(name = "count")
    private Map<Integer, Integer> orderedProducts;*/

    /*private OrderList orderedProducts;*/

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "products_to_order",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> orderedProducts;


    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "completed")
    private Boolean completed;

    /*public void addProductToOrder(Integer product, Integer amount)
    {
        if (this.getOrderedProducts() == null) {
            this.orderedProducts = new LinkedHashMap<>();
            orderedProducts.put(product, amount);
        }
        else this.orderedProducts.put(product, amount);
    }*/

    public void addProductToOrder(Product product)
    {
        if (getOrderedProducts() == null) {
            orderedProducts = new HashSet<>();
            orderedProducts.add(product);
        }
        else orderedProducts.add(product);
    }
}
