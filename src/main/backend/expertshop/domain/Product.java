package expertshop.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "products")
public class Product implements Serializable {
    /*ОТ ПОСТАВШИКОВ В БД БЕЗ РЕДАКТИРОВАНИЯ*/
    @Id
    @Column(name = "product_id")
    public String productID;

    private String originalCategory, originalType, originalBrand, originalAmount, originalPrice;

    @Column(name = "original_name", length = 10000)
    private String originalName;

    @Column(name = "original_annotation", length = 20000)
    private String originalAnnotation;

    @Column(name = "original_pic", length = 10000)
    private String originalPic;

    private String originalGroup;

    /*ОБЩИЕ ПОЛЯ*/
    private String supplier;

    private LocalDate update;

    /*ДЛЯ ВЫВОДА НА СТРАНИЦУ*/
    private String productGroup, productType, singleType, modelName, fullName, productCategory, groupBrand;

    private Integer finalPrice, bonus;

    private Double defaultCoefficient, modCoefficient;

    private Boolean isDuplicate, hasDuplicates, isAvailable;

    @Column(name = "formatted_annotation", length = 20000)
    private String formattedAnnotation;

    @JsonIgnore
    private String rType;

    @JsonIgnore
    @Column(length = 20000)
    private String rName;

    @Column(length = 20000)
    private String fullAnnotation;

    @Column(length = 20000)
    private String pics;

    private String shortModel, shortSearchName;

    private String linkR;

    @Column(length = 2000)
    private String localPic;

    private Boolean coefficientMod, priceMod, brandPrice;
}


