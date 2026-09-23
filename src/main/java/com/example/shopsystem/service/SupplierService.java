package com.example.shopsystem.service;

import com.example.shopsystem.model.Supplier;
import com.example.shopsystem.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;

    public List<Supplier> findAll() { return supplierRepository.findAll(); }

    public Supplier findById(String id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Supplier not found: " + id));
    }

    public Supplier save(Supplier supplier) { return supplierRepository.save(supplier); }

    public void delete(String id) { supplierRepository.deleteById(id); }

    public long count() { return supplierRepository.count(); }
}