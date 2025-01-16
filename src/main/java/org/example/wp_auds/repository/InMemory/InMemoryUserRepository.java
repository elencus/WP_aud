package org.example.wp_auds.repository.InMemory;

import org.example.wp_auds.bootstrap.dataholder;
import org.example.wp_auds.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryUserRepository {

    public Optional<User> findByUsername(String username){
        return dataholder.users.stream().filter(r->r.getUsername().equals(username)).findFirst();
    }

    public Optional<User> findByUsernameAndPassword(String username, String password){
        return dataholder.users.stream().filter(r->r.getUsername().equals(username) && r.getPassword().equals(password)).findFirst();
    }

    public User saveOrUpdate(User user){
        dataholder.users.removeIf(r->r.getUsername().equals(user.getUsername()));
        dataholder.users.add(user);
        return user;
    }

    public void delete(String username){
        dataholder.users.removeIf(r->r.getUsername().equals(username));
    }


}
