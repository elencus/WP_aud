package org.example.wp_auds.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;
    private int quantity;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Manufacturer manufacturer;


    public Product(String name, double price, int quantity,
                   Category category, Manufacturer manufacturer){
        this.name=name;
        this.price=price;
        this.quantity=quantity;
        this.category=category;
        this.manufacturer=manufacturer;
    }

    public Product() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Category getCategory() {
        return category;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }
}
