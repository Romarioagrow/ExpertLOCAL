package expertshop.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
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
    private Long productID;

    @OneToOne(optional = false, mappedBy = "productParams")
    @JsonIgnore
    private Product product;

    @Column(name = "type")
    private String type;

    //TV
    @Nullable
    private String diagonal, resolution, tvFeatures;

    //Stoves
    @Nullable
    private String stoveDimensions;
}
