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
    private Integer productID;

    @JsonIgnore
    @OneToOne(optional = false, mappedBy = "productParams", fetch = FetchType.EAGER)
    private Product product;

    @Column(name = "type")
    private String type;

    @Column(name = "type_category")
    private String typeCategory;

    //private String pic;

    private Integer amount;

    /*TV*/
    @Nullable
    private String diagonal, resolution, tvFeatures;

    /*Stoves*/
    @Nullable
    private String stoveDimensions;
}
