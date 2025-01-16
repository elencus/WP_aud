package org.example.wp_auds.repository.InMemory;

import org.example.wp_auds.bootstrap.dataholder;
import org.example.wp_auds.model.ShoppingCart;
import org.example.wp_auds.model.enumerations.ShoppingCartStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Repository

public class InMemoryShoppingCartRepository {

    public Optional<ShoppingCart> findById(Long id){
        return dataholder.shoppingCarts.stream().filter(r->r.getId().equals(id)).findFirst();
    }
    public Optional<ShoppingCart> findByUsernameAndStatus (String username, ShoppingCartStatus status){
        return dataholder.shoppingCarts.stream().filter(r->r.getUser().getUsername().equals(username) && r.getStatus().equals(status)).findFirst();
    }

    public ShoppingCart save(ShoppingCart shoppingCart){
        dataholder.shoppingCarts.removeIf(r->r.getUser().getUsername().equals(shoppingCart.getUser().getUsername()));
        dataholder.shoppingCarts.add(shoppingCart);
        return shoppingCart;
    }
}
