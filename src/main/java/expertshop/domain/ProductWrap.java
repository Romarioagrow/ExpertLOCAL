package expertshop.domain;
import expertshop.domain.categories.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products_parameters")
public class ProductWrap implements Serializable {
    @Id
    private Long product_id;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_parameters_id")
    private ProductParams productParams;

    @Enumerated(EnumType.STRING)
    private Type type;
}
