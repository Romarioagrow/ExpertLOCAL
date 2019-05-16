package expertshop.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Log
@Data
@Entity
@Table(name = "ordr")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "orderedProducts")
public class Order implements Serializable {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Data
    public class OrderToShow {
        public Integer showTotalPrice, productsAmount, totalProducts;
        public OrderToShow() {
            this.showTotalPrice = getTotalPrice();
            this.productsAmount = getOrderedProducts().size();
            this.totalProducts  = getTotalProductsAmount();
        }
    }

    public void addProductToOrder(OrderedProduct orderedProduct) {
        if (this.getOrderedProducts() == null) {
            this.orderedProducts = new HashSet<>();
            orderedProducts.add(orderedProduct);
        }
        else this.orderedProducts.add(orderedProduct);
    }

    public void setTotalPrice() {
        this.setTotalPrice(0);
        this.orderedProducts.forEach(orderedProduct -> this.totalPrice += orderedProduct.getTotalPrice());
        log.info(this.getTotalPrice().toString());
    }

    public Integer getTotalProductsAmount() {
        Integer totalProductsAmount = 0;
        for (OrderedProduct product : this.orderedProducts){
            totalProductsAmount += product.getAmount();
        }
        return totalProductsAmount;
    }

    public void removeProductFromOrder(OrderedProduct orderedProduct) {
        orderedProducts.remove(orderedProduct);
    }
}

