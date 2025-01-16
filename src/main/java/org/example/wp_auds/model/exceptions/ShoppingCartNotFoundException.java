package org.example.wp_auds.model.exceptions;

import org.example.wp_auds.model.ShoppingCart;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ShoppingCartNotFoundException extends RuntimeException {
    public ShoppingCartNotFoundException(Long id){
        super(String.format("Shopping cart not found %d", id));
    }
}
