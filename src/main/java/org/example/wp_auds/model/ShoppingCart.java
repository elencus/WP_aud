package org.example.wp_auds.model;

import jakarta.persistence.*;
import lombok.Data;
import org.example.wp_auds.model.enumerations.ShoppingCartStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private LocalDateTime dateCreated;
    @ManyToOne
    private User user;
    @ManyToMany
    private List<Product> products;
    @Enumerated(EnumType.STRING)
    private ShoppingCartStatus status;

    public ShoppingCart(){
        this.id=(long) (Math.random()*1000);
    }

    public ShoppingCart(User user) {
        this.dateCreated = LocalDateTime.now();
        this.id=(long) (Math.random()*1000);
        this.user = user;
        this.products = new ArrayList<>();
        this.status = ShoppingCartStatus.CREATED;
    }

    public ShoppingCart(User user, List<Product> products) {
        this.user = user;
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public User getUser() {
        return user;
    }

    public ShoppingCartStatus getStatus() {
        return status;
    }
}
