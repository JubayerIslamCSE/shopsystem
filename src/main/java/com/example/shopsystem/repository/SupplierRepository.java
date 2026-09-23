package com.example.shopsystem.repository;

import com.example.shopsystem.model.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SupplierRepository extends MongoRepository<Supplier, String> {
}