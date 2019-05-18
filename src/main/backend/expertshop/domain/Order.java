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

    private Boolean  accepted, confirmed, completed;

    private Integer totalPrice, productsAmount, totalAmount;
    private String name, surname, mobile, email, address;

    public void addProductToOrder(OrderedProduct orderedProduct)
    {
        if (this.getOrderedProducts() == null) {
            this.orderedProducts = new HashSet<>();
            orderedProducts.add(orderedProduct);
        }
        else this.orderedProducts.add(orderedProduct);
    }

    public Integer getTotalOrderPrice()
    {
        Integer totalPrice = 0;
        for (OrderedProduct product : orderedProducts) {
            totalPrice += product.getTotalPrice();
        }

        log.info("Total price: " + getTotalPrice().toString());
        return totalPrice;
    }

    public Integer getTotalProductsAmount()
    {
        Integer totalAmount = 0;
        for (OrderedProduct product : orderedProducts) {
            totalAmount += product.getAmount();
        }

        log.info("Total amount: " + getTotalAmount().toString());
        return totalAmount;
    }
}

