package expertshop.domain;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "parameters")
class Parameters{
    @Id
    private Long product_id;
    private String type_name;
    private Integer type_id;
    private String resolution;
    private String diagonal;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> product;
}
