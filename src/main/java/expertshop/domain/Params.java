package expertshop.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import javax.persistence.*;
import java.util.List;

@Entity
/*@Data*/
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


    public Long getProduct_id() {
        return product_id;
    }
    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }
    public String getType_name() {
        return type_name;
    }
    public void setType_name(String type_name) {
        this.type_name = type_name;
    }
    public Integer getType_id() {
        return type_id;
    }
    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }
    public List<Product> getProduct() {
        return product;
    }
    public void setProduct(List<Product> product) {
        this.product = product;
    }
    @Nullable
    public Integer getDiagonal() {
        return diagonal;
    }
    public void setDiagonal(@Nullable Integer diagonal) {
        this.diagonal = diagonal;
    }
    @Nullable
    public Integer getUpdate_frequency() {
        return update_frequency;
    }
    public void setUpdate_frequency(@Nullable Integer update_frequency) {
        this.update_frequency = update_frequency;
    }
    @Nullable
    public String getResolution() {
        return resolution;
    }
    public void setResolution(@Nullable String resolution) {
        this.resolution = resolution;
    }
    @Nullable
    public String getResolution_type() {
        return resolution_type;
    }
    public void setResolution_type(@Nullable String resolution_type) {
        this.resolution_type = resolution_type;
    }
    @Nullable
    public Boolean getHasSmartTV() {
        return hasSmartTV;
    }
    public void setHasSmartTV(@Nullable Boolean hasSmartTV) {
        this.hasSmartTV = hasSmartTV;
    }
    @Nullable
    public Boolean getHasWifi() {
        return hasWifi;
    }
    public void setHasWifi(@Nullable Boolean hasWifi) {
        this.hasWifi = hasWifi;
    }
    @Nullable
    public Boolean getCurved_screen() {
        return curved_screen;
    }
    public void setCurved_screen(@Nullable Boolean curved_screen) {
        this.curved_screen = curved_screen;
    }
}
