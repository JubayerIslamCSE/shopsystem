package com.example.shopsystem.controller;

import com.example.shopsystem.service.CategoryService;
import com.example.shopsystem.service.ProductService;
import com.example.shopsystem.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final SupplierService supplierService;

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("productCount", productService.count());
        model.addAttribute("categoryCount", categoryService.count());
        model.addAttribute("supplierCount", supplierService.count());
        model.addAttribute("inventoryValue", productService.totalInventoryValue());
        model.addAttribute("lowStock", productService.lowStock());
        return "index";
    }
}