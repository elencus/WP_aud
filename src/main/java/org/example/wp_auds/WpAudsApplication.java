package org.example.wp_auds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class WpAudsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WpAudsApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new
                BCryptPasswordEncoder(10);
    }
}
