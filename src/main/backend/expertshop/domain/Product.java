package expertshop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @Column(name = "product_id")
    public String productID;

    @Column(name = "category")
    private String category;

    @Column(name = "subcategory")
    private String subCategory;

    @Column(name = "product_group")
    private String productGroup;

    private String type, supplier, modelName, brand, finalPrice, amount;

    @Column(length = 25000)
    private String fullName;

    @Column(length = 25000)
    private String pic;

    @Column(name = "price")
    private String price;

    @Column(length = 25000)
    private String annotation;

    @Column(length = 25000)
    private String shortHtmlInfo;

    @Column(length = 25000)
    private String pics;

    @Column(length = 25000)
    private String fullInfo;

    private String groupAndBrand;

    @Column(length = 2500)
    private String _tv_resol, _tv_diag;
}


