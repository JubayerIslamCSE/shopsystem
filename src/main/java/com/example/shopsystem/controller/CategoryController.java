package com.example.shopsystem.controller;

import com.example.shopsystem.model.Category;
import com.example.shopsystem.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "categories/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("category", new Category());
        return "categories/form";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("category", categoryService.findById(id));
        return "categories/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("category") Category category, BindingResult result) {
        if (result.hasErrors()) return "categories/form";
        categoryService.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        categoryService.delete(id);
        return "redirect:/categories";
    }
}