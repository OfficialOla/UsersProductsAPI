package org.example.controllers;

import lombok.AllArgsConstructor;
import org.example.dtos.AddProductRequest;
import org.example.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/product")
@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody AddProductRequest addProductRequest){
        var response = productService.addProduct(addProductRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
