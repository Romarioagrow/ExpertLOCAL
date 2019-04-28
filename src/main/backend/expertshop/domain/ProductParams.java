package expertshop.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;

import expertshop.domain.categories.Type;
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
    private Long productID;

    @OneToOne(optional = false, mappedBy = "productParams")
    @JsonIgnore
    private Product product;

    @Column(name = "type")
    private String type;

    //TV
    @Nullable
    private String diagonal;
    @Nullable
    private String resolution;
    @Nullable
    private String tvFeatures;

    //Stoves
    @Nullable
    private String stoveDimensions;
}
