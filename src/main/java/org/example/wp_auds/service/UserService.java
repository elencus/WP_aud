package org.example.wp_auds.service;

import org.example.wp_auds.model.User;
import org.example.wp_auds.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User register(String username, String password, String name, String surname, Role role);

}
