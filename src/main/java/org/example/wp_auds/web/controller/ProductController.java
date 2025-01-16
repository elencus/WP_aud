package org.example.wp_auds.web.controller;

import org.example.wp_auds.model.Category;
import org.example.wp_auds.model.Manufacturer;
import org.example.wp_auds.model.Product;
import org.example.wp_auds.service.CategoryService;
import org.example.wp_auds.service.ManufacturerService;
import org.example.wp_auds.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    final  CategoryService categoryService;
    final ProductService productService;
    final ManufacturerService manufacturerService;

    public ProductController(CategoryService categoryService, ProductService productService, ManufacturerService manufacturerService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.manufacturerService = manufacturerService;
    }


    @GetMapping
    public String getProductPage(@RequestParam(required = false) String error, Model model){
        if (error!=null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Product> products=this.productService.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        this.productService.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/add-form")
    @PreAuthorize("HasRole('ROLE_ADMIN')")
    public String addProductPage(Model model){
        List<Category> categories = this.categoryService.listCategories();
        List<Manufacturer> manufacturers=this.manufacturerService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("bodyContent", "add-product");
        return "master-template";
    }

    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        if (this.productService.findById(id).isPresent()) {
            Product product = this.productService.findById(id).get();
            List<Category> categories = this.categoryService.listCategories();
            List<Manufacturer> manufacturers = this.manufacturerService.findAll();
            model.addAttribute("categories", categories);
            model.addAttribute("manufacturers", manufacturers);
            model.addAttribute("product", product);
            model.addAttribute("bodyContent", "add-product");
            return "master-template";
        }
        return "redirect:/products?error=ProductNotFound";
    }

    @PostMapping("/add")
    public String saveProduct(@RequestParam String name,
                              @RequestParam Double price,
                              @RequestParam int quantity,
                              @RequestParam Long category,
                              @RequestParam Long manufacturer){
        this.productService.save(name,price,quantity,category,manufacturer);
        return "redirect:/products";
    }
}
