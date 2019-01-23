package com.radek.restaurant.hibernate;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "menu_items")
@Data
@NoArgsConstructor
public class MenuItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ITEM")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CATEGORY", referencedColumnName = "ID_CATEGORY")
    private Category category;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "QUANTITY")
    private String quantity;

    @Column(name = "PRICE")
    private double price;

    public MenuItem(Category category, String name, String description, String quantity, double price) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }
}
