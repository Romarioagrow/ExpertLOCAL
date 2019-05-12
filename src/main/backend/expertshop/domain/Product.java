package expertshop.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;

import expertshop.domain.categories.*;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude="productParams")
@ToString(exclude="productParams" )
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product implements Serializable {
    @Id
    @Column(name = "product_id")
    public Integer productID;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "subcategory")
    @Enumerated(EnumType.STRING)
    private SubCategory subCategory;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    private String brand, model, country;
    private Integer price;

    @OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private ProductParams productParams;

    @JsonIgnore
    @ManyToMany(mappedBy = "orderedProducts", fetch = FetchType.EAGER)
    private List<Order> orders;

    @JsonIgnore
    public String getTypeName() {
        return productParams.getType();
    }
    @JsonIgnore
    public String getDiagonal() {
        return productParams.getDiagonal();
    }
    @JsonIgnore
    public String getResolution() {
        return productParams.getResolution();
    }
    @JsonIgnore
    public String getTvFeatures() {
        return productParams.getTvFeatures();
    }
    @JsonIgnore
    public String getStoveDimensions() {
        return productParams.getStoveDimensions();
    }
}


