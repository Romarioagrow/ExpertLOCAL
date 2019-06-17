package expertshop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @Column(name = "product_id")
    public String productID;

    @Column(name = "category")
    private String category;

    @Column(name = "subcategory")
    private String subCategory;

    @Column(name = "product_group")
    private String productGroup;

    private String type;

    private String supplier;

    @Column(length = 25000)
    private String fullName;

    private String modelName;

    @Column(length = 25000)
    private String pic;

    private String brand, finalPrice;

    @Column(name = "price")
    private String price;

    @Column(length = 25000)
    private String annotation;

    private String amount;


    /*
    @OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(modelName = "product_id")
    private ProductParams productParams;

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
    }*/
}


