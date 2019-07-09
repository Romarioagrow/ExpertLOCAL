package expertshop.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products1")
public class Product implements Serializable {
    /*ОТ ПОСТАВШИКОВ В БД БЕЗ РЕДАКТИРОВАНИЯ*/
    @Id
    @Column(name = "product_id")
    public String productID;

    @Column(name = "original_category")
    private String originalCategory;

    /*@Column(name = "original_subcategory")
    private String originalSubCategory;

    @Column(name = "original_group")
    private String originalGroup;*/

    @Column(name = "original_type")
    private String originalType;

    @Column(name = "original_brand")
    private String originalBrand;

    @Column(name = "original_name", length = 10000)
    private String originalName;

    @Column(name = "original_annotation", length = 20000)
    private String originalAnnotation;

    @Column(name = "original_amount")
    private String originalAmount;

    @Column(name = "original_price")
    private String originalPrice;

    @Column(name = "original_pic", length = 10000)
    private String originalPic;

    /*ОБЩИЕ ПОЛЯ*/
    private String supplier;

    @Column(name = "available_to_delivery")
    private Boolean availableToDelivery = false;

    @Column(name = "available_now")
    private Boolean availableNow        = false;

    private LocalDate update;

    /*ДЛЯ ВЫВОДА НА СТРАНИЦУ*/
    @Column(name = "product_group")
    private String productGroup;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "product_single_type")
    private String singleType;

    @Column(name = "product_model_name")
    private String modelName;

    @Column(name = "product_sale_name")
    private String saleName;

    private Integer price, finalPrice, bonus;

    @Column(name = "product_category")
    private String productCategory;

    @Column(name = "type_brand")
    private String typeBrand;

    @Column(name = "duplicate")
    private Boolean isDuplicate = false;

    @Column(name = "formatted_annotation", length = 20000)
    private String formattedAnnotation;
}


