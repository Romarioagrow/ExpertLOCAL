package expertshop.domain;
import expertshop.domain.categories.Category;
import expertshop.domain.categories.SubCategory;
import expertshop.domain.categories.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product implements Serializable {
    @Id
    private Long product_id;

    @OneToOne(optional = false, mappedBy = "product")
    private ProductWrap wrap;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category; // Техника для дома

    @Column(name = "subcategory")
    @Enumerated(EnumType.STRING)
    private SubCategory subCategory; // Техника для уборки

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type; // Холодильник

    private String brand; // LG
    private String model; // PSJ 600
    private String country; // Korea
    private Integer price; // 8800



    /*@ManyToMany(mappedBy = "product",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ProductParams> parameters;*/

}
