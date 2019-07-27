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
    @Column(name = "product_auto_id")
    public String productID;

    private String  fullName, brand;

    @Column(length = 250000)
    private String annotation;

    @Column(length = 250000)
    private String pics;

    @Column(length = 250000)
    private String formattedAnnotation;

    private String modelName, shortModel;
}
