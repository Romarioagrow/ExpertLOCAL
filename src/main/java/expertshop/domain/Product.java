package expertshop.domain;
import expertshop.domain.lists.Categories;
import expertshop.domain.lists.Types;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long product_id;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Categories category; // Кухонная техника /// В ОТДЕЛЬНУЮ ТАБЛИЦУ РОЛЕЙ (ENUM)

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Types type; // Холодильник /// В ОТДЕЛЬНУЮ ТАБЛИЦУ ТИПОВ (ENUM)

    private String brand; // LG

    private String model; // PSJ 600

    private String country; // Korea

    private Integer price; // 8800

    @ManyToMany(mappedBy = "product",fetch = FetchType.EAGER)
    private List<Params> parameters;


/*    private List<AllFeatures> features;*/
}
