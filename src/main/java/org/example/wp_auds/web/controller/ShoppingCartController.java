package org.example.wp_auds.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.wp_auds.model.ShoppingCart;
import org.example.wp_auds.model.User;
import org.example.wp_auds.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

@GetMapping
    public String getShoppingCartPage(@RequestParam(required = false) String error, HttpServletRequest req, Model model) {
    if (error != null && error.isEmpty()) {
        model.addAttribute("has Error", true);
        model.addAttribute("error", error);
    }
    User user = (User) req.getSession().getAttribute("user");
    ShoppingCart shoppingCart=this.shoppingCartService.getActiveShoppingCart(user.getUsername());
    model.addAttribute("products", shoppingCartService.listAllProductsInShoppingCart(shoppingCart.getId()));
    return "shopping-cart";
    }
    @PostMapping("/add-product/{id}")
    public String addProductToShoppingCart(@PathVariable Long id, HttpServletRequest req){
        try{
            User user=(User) req.getSession().getAttribute("user");
            ShoppingCart shoppingCart=this.shoppingCartService.addProductToShoppingCart(user.getUsername(), id);
            return "redirect:/shopping-cart";
        }catch (RuntimeException exception){
            return "redirect:/shopping-car?error" + exception.getMessage();
        }

    }
}
