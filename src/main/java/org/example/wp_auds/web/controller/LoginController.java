package org.example.wp_auds.web.controller;

import org.example.wp_auds.model.User;
import org.example.wp_auds.model.exceptions.InvalidUserCredentialException;
import org.example.wp_auds.service.AuthService;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String getLoginPage(){
        return "login";
    }

    @PostMapping
    public  String login(HttpServletRequest request, Model model){
        User user = null;
        try{
            user = this.authService.login(request.getParameter("username"),
                    request.getParameter("password"));
            request.getSession().setAttribute("user", user);
            return "redirect:/home";
        }
        catch (InvalidUserCredentialException exception){
            model.addAttribute("has Error", true);
            model.addAttribute("error", exception.getMessage());
            return "login";

        }
    }
}
