package expertshop.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
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
    private Long orderID;

    @Column(name = "session_uuid")
    private String sessionUUID;

    @Column(name = "user_id")
    private Long userID;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "products_to_order",
            joinColumns         = @JoinColumn(name = "order_id"),
            inverseJoinColumns  = @JoinColumn(name = "ordered_product"))
    private Set<OrderedProduct> orderedProducts;

    private Boolean accepted        = false;
    private Boolean confirmed       = false;
    private Boolean completed       = false;
    private Boolean discountApplied = false;

    private Integer totalPrice      = 0;
    private Integer productsAmount  = 0;
    private Integer totalAmount     = 0;
    private Integer totalDiscount   = 0;
    private Integer totalBonus, bonusOff = 0; /// bonusOff @Column(name = "discount")
    private Integer discountPercent, discountPrice;

    private String name, otchestvo;
    private String surname;
    private String mobile;
    private String email;
    private String city, street, house, apartment;
    private String shortTel, deliveryType;

    @Column(name = "open_date")
    private LocalDateTime orderOpenDate = LocalDateTime.now();

    public Boolean isAccepted() {
        return accepted;
    }

    public void addProductToOrder(OrderedProduct orderedProduct) {
        if (this.getOrderedProducts() == null)
        {
            this.orderedProducts = new HashSet<>();
            orderedProducts.add(orderedProduct);
        }
        else this.orderedProducts.add(orderedProduct);
    }

    public Integer extractTotalOrderPrice() {
        Integer totalPrice = 0;
        for (OrderedProduct product : orderedProducts) {
            totalPrice += product.getTotalPrice();
        }
        return totalPrice;
    }
    public Integer extractTotalProductsAmount() {
        Integer orderedAmount = 0;
        for (OrderedProduct product : orderedProducts) {
            orderedAmount += product.getOrderedAmount();
        }
        return orderedAmount;
    }
    public Integer extractTotalBonus() {
        int totalBonus = 0;
        for (OrderedProduct product : orderedProducts) {
            totalBonus += product.getBonus() * product.getOrderedAmount();
        }
        return totalBonus;
    }
}

