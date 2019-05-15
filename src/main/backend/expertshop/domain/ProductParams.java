package expertshop.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;

import expertshop.domain.categories.SubCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "parameters")
public class ProductParams implements Serializable {
    @Id
    @JsonIgnore
    @Column(name = "product_id")
    private Integer productID;

    @OneToOne(optional = false, mappedBy = "productParams", fetch = FetchType.EAGER)
    @JsonIgnore
    private Product product;

    @Column(name = "type")
    private String type;

    @Column(name = "type_category")
    private String typeCategory;

    private String pic;

    private Integer amount;
    //TV
    @Nullable
    private String diagonal, resolution, tvFeatures;

    //Stoves
    @Nullable
    private String stoveDimensions;
}


/*@OneToOne(optional = false, mappedBy = "orderedProductParams", fetch = FetchType.EAGER)
    @JsonIgnore
    private OrderedProduct productToOrder;*/