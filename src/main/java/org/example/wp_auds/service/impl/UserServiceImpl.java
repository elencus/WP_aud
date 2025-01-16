package org.example.wp_auds.service.impl;

import org.example.wp_auds.model.User;
import org.example.wp_auds.model.enumerations.Role;
import org.example.wp_auds.model.exceptions.InvalidArgumentException;
import org.example.wp_auds.model.exceptions.UsernameAlreadyExistsException;
import org.example.wp_auds.repository.jpa.UserRepository;
import org.example.wp_auds.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String name, String surname, Role role) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentException();
        }
        if (this.userRepository.findByUsername(username).isPresent()){
            throw new UsernameAlreadyExistsException(username);
        }
        User user = new User(username, passwordEncoder.encode(password), name, surname, role);
        userRepository.save(user);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(username));
    }
}
