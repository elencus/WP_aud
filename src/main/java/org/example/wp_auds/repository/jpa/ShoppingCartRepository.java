package org.example.wp_auds.repository.jpa;

import org.example.wp_auds.model.ShoppingCart;
import org.example.wp_auds.model.User;
import org.example.wp_auds.model.enumerations.ShoppingCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);
}
