package org.example.wp_auds.service;

import org.example.wp_auds.model.User;

public interface AuthService {

    User login(String username, String password);

}
