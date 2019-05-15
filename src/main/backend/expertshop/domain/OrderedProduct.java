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

    @ManyToMany(mappedBy = "orderedProducts")
    @JsonIgnore
    private Set<Order> orders;
}

//@Column(name = "product_id")
//@OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)

    /*@OneToOne(optional = false, mappedBy = "orderedProduct", fetch = FetchType.EAGER)
    @JsonIgnore
    private Product productID;*/
    /*@OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;*/

