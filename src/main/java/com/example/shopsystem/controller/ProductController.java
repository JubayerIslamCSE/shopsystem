package com.example.shopsystem.controller;

import com.example.shopsystem.model.Category;
import com.example.shopsystem.model.Product;
import com.example.shopsystem.model.Supplier;
import com.example.shopsystem.service.CategoryService;
import com.example.shopsystem.service.ProductService;
import com.example.shopsystem.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final SupplierService supplierService;

    @GetMapping
    public String list(@RequestParam(required = false) String q, Model model) {
        model.addAttribute("products", productService.search(q));
        model.addAttribute("q", q);
        return "products/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("product", new Product());
        addDropdowns(model);
        return "products/form";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("product", productService.findById(id));
        addDropdowns(model);
        return "products/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("product") Product product,
                       BindingResult result, Model model) {
        if (result.hasErrors()) {
            addDropdowns(model);
            return "products/form";
        }
        // resolve and store the category/supplier names
        if (product.getCategoryId() != null && !product.getCategoryId().isBlank()) {
            Category c = categoryService.findById(product.getCategoryId());
            product.setCategoryName(c.getName());
        } else {
            product.setCategoryName(null);
        }
        if (product.getSupplierId() != null && !product.getSupplierId().isBlank()) {
            Supplier s = supplierService.findById(product.getSupplierId());
            product.setSupplierName(s.getName());
        } else {
            product.setSupplierName(null);
        }
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        productService.delete(id);
        return "redirect:/products";
    }

    private void addDropdowns(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("suppliers", supplierService.findAll());
    }
}