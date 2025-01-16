package org.example.wp_auds.model.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException{
    public PasswordsDoNotMatchException(){
        super("Passwords do not match!");
    }
}
