package expertshop.domain;
import expertshop.domain.categories.Category;
import expertshop.domain.categories.SubCategory;
import expertshop.domain.categories.Type;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
/*
@Data
*/
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    private Long product_id;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category; // Техника для дома /// В ОТДЕЛЬНУЮ ТАБЛИЦУ РОЛЕЙ (ENUM)

    @Column(name = "subcategory")
    @Enumerated(EnumType.STRING)
    private SubCategory subCategory; // Техника для уборки /// В ОТДЕЛЬНУЮ ТАБЛИЦУ РОЛЕЙ (ENUM)

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type; // Холодильник /// В ОТДЕЛЬНУЮ ТАБЛИЦУ ТИПОВ (ENUM)

    private String brand; // LG

    private String model; // PSJ 600

    private String country; // Korea

    private Integer price; // 8800

    @ManyToMany(mappedBy = "product",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Params> parameters;


    public Long getProduct_id() {
        return product_id;
    }
    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public List<Params> getParameters() {
        return parameters;
    }
    public void setParameters(List<Params> parameters) {
        this.parameters = parameters;
    }
}
