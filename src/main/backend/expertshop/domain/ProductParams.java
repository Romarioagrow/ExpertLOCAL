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
//@EqualsAndHashCode(exclude="product")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "parameters")
public class ProductParams implements Serializable {
    @OneToOne(optional = false, mappedBy = "productParams", fetch = FetchType.EAGER)
    @JsonIgnore
    private Product product;

    @Id
    @JsonIgnore
    @Column(name = "product_id")
    private Integer productID;

    @Column(name = "type")
    private String type;

    @Column(name = "type_category")
    private String typeCategory;

    private String pic;
    //TV
    @Nullable
    private String diagonal, resolution, tvFeatures;

    //Stoves
    @Nullable
    private String stoveDimensions;
}
