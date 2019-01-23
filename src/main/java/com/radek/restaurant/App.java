package com.radek.restaurant;


import com.radek.restaurant.hibernate.Menu;
import com.radek.restaurant.hibernate.MenuItem;

import java.util.Optional;

public class App {

    public static void main(String[] args) {

        var database = Database.getInstance();

        Optional<Menu> menuOptional = database.getMenuByName("lato");

        System.out.println(menuOptional.isPresent());



        Optional<MenuItem> menuItemOptional = database.getItemByName("Schabowy");

        System.out.println(menuItemOptional.get().getDescription());
    }
}
