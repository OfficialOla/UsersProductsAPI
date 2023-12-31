package org.example.repositories;

import org.example.dtos.ViewProductResponse;
import org.example.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findProductByProductName(String name);
}
