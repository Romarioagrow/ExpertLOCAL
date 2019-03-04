package expertshop.entities;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String brand; // LG

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Categories category; // Кухонная техника /// В ОТДЕЛЬНУЮ ТАБЛИЦУ РОЛЕЙ (ENUM)

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Types type; // Холодильник /// В ОТДЕЛЬНУЮ ТАБЛИЦУ ТИПОВ (ENUM)

    private String model; // PSJ 600
    private String country; // Korea
    private Integer price; // 8800
}
