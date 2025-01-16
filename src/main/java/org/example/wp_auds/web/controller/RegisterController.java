package org.example.wp_auds.web.controller;

import org.example.wp_auds.model.enumerations.Role;
import org.example.wp_auds.model.exceptions.InvalidArgumentException;
import org.example.wp_auds.model.exceptions.PasswordsDoNotMatchException;
import org.example.wp_auds.service.AuthService;
import org.example.wp_auds.service.UserService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class RegisterController {

    private final AuthService authService;
    private final UserService userService;
    public RegisterController(AuthService authService, UserService userService){
        this.authService=authService;
        this.userService=userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        return "register";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam Role role) {
        try {
            this.userService.register(username, password, name, surname, role);
            return "redirect:/login";
        }
        catch (PasswordsDoNotMatchException |InvalidArgumentException exception){
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}
