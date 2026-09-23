package com.example.shopsystem.controller;

import com.example.shopsystem.model.Supplier;
import com.example.shopsystem.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/suppliers")
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService supplierService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("suppliers", supplierService.findAll());
        return "suppliers/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "suppliers/form";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("supplier", supplierService.findById(id));
        return "suppliers/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("supplier") Supplier supplier, BindingResult result) {
        if (result.hasErrors()) return "suppliers/form";
        supplierService.save(supplier);
        return "redirect:/suppliers";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        supplierService.delete(id);
        return "redirect:/suppliers";
    }
}