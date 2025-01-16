package org.example.wp_auds.repository.jpa;

import org.example.wp_auds.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User>findByUsernameAndPassword(String username,String password);
    Optional<User>findByUsername(String username);
}
