package com.example.webspringbootfamework.controller;

import com.example.webspringbootfamework.entity.Product;
import com.example.webspringbootfamework.repon.ProductRepon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductRepon productRepon;

    @GetMapping("/")
    public String productList(Model model) {
        List<Product> products = productRepon.findAll();
        model.addAttribute("products", products);
        return "product-list"; // Ensure this file exists in src/main/resources/templates
    }

    @GetMapping("/create")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "create-product";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productRepon.save(product);
        return "redirect:/"; // Redirect to root URL to show updated product list
    }

    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        Product product = productRepon.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("product", product);
        return "edit-product";
    }
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {
        Product product1 = productRepon.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Invalid product Id:" + id));
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        product1.setDescription(product.getDescription());
        productRepon.save(product1);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepon.deleteById(id);
        return "redirect:/";
    }
}
