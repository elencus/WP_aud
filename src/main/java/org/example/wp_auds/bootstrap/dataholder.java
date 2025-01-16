package org.example.wp_auds.bootstrap;

import java.util.ArrayList;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.example.wp_auds.model.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
public class dataholder {
    public static List<Category> categories = new ArrayList<>();
    public static List<User> users = new ArrayList<>();
    public static List<Manufacturer> manufacturers=new ArrayList<>();
    public static List<Product>products=new ArrayList<>();
    public static List<ShoppingCart>shoppingCarts=new ArrayList<>();

    @PostConstruct
    public void init(){
        categories.add(new Category("Books", "Books category"));
        categories.add(new Category("Movies", "Movies category"));
        categories.add(new Category("Software", "Software category"));

        users.add(new User("elena.taleska", "et", "elena", "taleska"));
        users.add(new User("kire.taleski", "kt", "kire", "taleski"));

        Manufacturer manufacturer=new Manufacturer("Nike", "Ny");
        manufacturers.add(manufacturer);
        Category category=new Category("Sport", "Sport category");
        categories.add(category);
        products.add(new Product("Ball 1 ",235.8,7,category, manufacturer));
        products.add(new Product("Ball 2",235.8,7,category, manufacturer));
        products.add(new Product("Ball 3",235.8,7,category, manufacturer));
    }

}
