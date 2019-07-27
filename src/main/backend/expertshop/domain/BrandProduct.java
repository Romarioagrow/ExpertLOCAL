package expertshop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "brands")
public class BrandProduct {
    @Id
    @Column(name = "product_id")
    public String productID;

    private String  fullName, brand;

    @Column(length = 250000)
    private String annotation;

    private String originalPrice, finalPrice, percent, shortModel, pic;
}
