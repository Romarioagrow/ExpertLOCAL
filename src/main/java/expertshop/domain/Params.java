package expertshop.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "parameters")
public class Params {
    @Id
    private Long product_id;
    private String type_name;
    private Integer type_id;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> product;

    /*INTEGER*/
    @Nullable
    private Integer diagonal;
    @Nullable
    private Integer update_frequency;

    /*STRING*/
    @Nullable
    private String resolution;
    @Nullable
    private String resolution_type;

    /*BOOLEAN*/
    @Nullable
    private Boolean hasSmartTV;
    @Nullable
    private Boolean hasWifi;
    @Nullable
    private Boolean curved_screen;


}
