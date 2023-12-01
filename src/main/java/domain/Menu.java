package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENU_ID")
    private Long id;

    @Column(name = "MENU_NAME", nullable = false)
    private String name;

    @Column(name = "MENU_PRICE", nullable = false)
    private Integer price;

    @Enumerated(EnumType.STRING)
    @Column(name = "MENU_CATEGORY", nullable = false)
    private Category category;

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }
}
