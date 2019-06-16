package expertshop.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;

import expertshop.domain.categories.*;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
/*@Inheritance(strategy = InheritanceType.SINGLE_TABLE)*/
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
/*@SuppressWarnings("PMD")*/
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
    private String name;

    private String brand, pic, finalPrice;

    @Column(name = "price")
    private String price;

    @Column(length = 25000)
    private String annotation;

    private String amount;


    /*
    @OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
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


