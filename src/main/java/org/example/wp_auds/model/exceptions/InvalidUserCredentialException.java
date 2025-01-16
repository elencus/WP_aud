package org.example.wp_auds.model.exceptions;

public class InvalidUserCredentialException extends RuntimeException{
    public InvalidUserCredentialException(){
        super("Invalid user credential exception");
    }
}
