package com.radek.restaurant.hibernate;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "menus")
@Data
@NoArgsConstructor
public class Menu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MENU")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="menu_categories", joinColumns={@JoinColumn(name="ID_MENU")},
            inverseJoinColumns={@JoinColumn(name="ID_CATEGORY")})
    private List<Category> categories;

    public Menu(String name, List<Category> categories) {
        this.name = name;
        this.categories = categories;
    }
}
