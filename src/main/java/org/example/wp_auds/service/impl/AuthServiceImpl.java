package org.example.wp_auds.service.impl;

import org.example.wp_auds.model.User;
import org.example.wp_auds.model.exceptions.InvalidArgumentException;
import org.example.wp_auds.model.exceptions.InvalidUserCredentialException;
import org.example.wp_auds.model.exceptions.UsernameAlreadyExistsException;
import org.example.wp_auds.repository.InMemory.InMemoryUserRepository;
import org.example.wp_auds.repository.jpa.UserRepository;
import org.example.wp_auds.service.AuthService;
import org.springframework.stereotype.Service;

import java.net.UnknownServiceException;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    public AuthServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()){
            throw new InvalidArgumentException();
        }
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(InvalidUserCredentialException::new);
    }

}
