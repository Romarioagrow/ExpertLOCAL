package expertshop.domain;
import expertshop.domain.categories.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "parameters")
public class ProductParams implements Serializable {
    @Id
    private Long productID;

    @OneToOne(optional = false, mappedBy = "productParams")
    private ProductWrap wrap;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    /*@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> product;*/

    /*STRING*/
    @Nullable
    private String resolution;
    @Nullable
    private String resolutionType;
    @Nullable
    private String diagonal;
    @Nullable
    private String updateFrequency;

    /*BOOLEAN*/
    @Nullable
    private Boolean hasSmartTV;
    @Nullable
    private Boolean hasWifi;
    @Nullable
    private Boolean curvedScreen;

}
