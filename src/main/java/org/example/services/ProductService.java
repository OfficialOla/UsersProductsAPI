package org.example.services;

import org.example.dtos.*;
import org.example.exceptions.ProductNotFoundException;

import java.util.List;

public interface ProductService {
    AddProductResponse addProduct(AddProductRequest addProductRequest);

    ViewProductResponse findProductById(int id) throws ProductNotFoundException;

    ViewProductResponse findProductByName(String productName) throws ProductNotFoundException;

    List<ViewProductResponse> findAllProducts(int page, int size);

    ApiResponse<?> deleteProductById(int id);

    void deleteAll();


}
