package expertshop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_base")
public class ProductBase {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_auto_id")
    public String productID;

    @Column(name = "category")
    private String category;

    @Column(name = "subcategory")
    private String subCategory;

    @Column(name = "product_group")
    private String productGroup;

    private String articul;

    @Column(name = "modification_articul")
    private String articulModification;

    private String modelName, fullName;

    @Column(name = "full_path")
    private String fullCategory;

    @Column(length = 250000)
    private String annotation;

    private String price, brand;

    @Column(length = 250000)
    private String/*[]*/ pics;

    @Column(length = 250000)
    private String paramsHTML;

    /*public void setPics(String s) {

    }*/
}
