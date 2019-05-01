package expertshop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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
    private Integer sessionUUID;

    @Column(name = "user_id")
    private Integer userID;

    /*@Column(name = "products")
    @ManyToMany
    private Set<Product> orderedProducts;*/
}
