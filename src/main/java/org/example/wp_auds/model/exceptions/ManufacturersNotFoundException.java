package org.example.wp_auds.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ManufacturersNotFoundException extends RuntimeException{
    public ManufacturersNotFoundException(Long id){
        super(String.format("Manufacturer with %d was not found.", id));
    }
}
