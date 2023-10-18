package org.example.services;

import lombok.AllArgsConstructor;
import org.example.dtos.AddProductRequest;
import org.example.dtos.AddProductResponse;
import org.example.dtos.ApiResponse;
import org.example.dtos.ViewProductResponse;
import org.example.exceptions.ProductNotFoundException;
import org.example.models.Product;
import org.example.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import static org.example.utils.AppUtils.buildPageRequest;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public AddProductResponse addProduct(AddProductRequest addProductRequest) {
        Product product = modelMapper.map(addProductRequest, Product.class);
        Product savedProduct = productRepository.save(product);
        return buildAddProductRequest(savedProduct.getId());
    }
    private AddProductResponse buildAddProductRequest(Long id) {
        AddProductResponse addProductResponse = new AddProductResponse();
        addProductResponse.setMessage("Product added successfully");
        addProductResponse.setId(id);
        return addProductResponse;
    }

    @Override
    public ViewProductResponse findProductById(int id) throws ProductNotFoundException {
        Optional<Product> foundProduct = productRepository.findById(id);
        Product appProduct = foundProduct.orElseThrow(()-> new ProductNotFoundException(
                String.format("Product with %s not found", id)
        ));
        return buildFindProductResponse(appProduct);
    }

    @Override
    public ViewProductResponse findProductByName(String productName) throws ProductNotFoundException {
        Optional<Product> foundProduct = productRepository.findProductByProductName(productName);
        Product response =  foundProduct.orElseThrow(()-> new ProductNotFoundException("Product not found"));
        return buildFindProductResponse(response);
    }

    private static ViewProductResponse buildFindProductResponse(Product response) {
        return ViewProductResponse.builder()
                .productName(response.getProductName())
                .price(response.getPrice())
                .description(response.getDescription())
                .build();
    }

    @Override
    public List<ViewProductResponse> findAllProducts(int page, int size) {
        Pageable pageable = buildPageRequest(page, size);
        Page<Product> productPage = productRepository.findAll(pageable);
        List<Product> products = productPage.getContent();
        return products.stream()
                .map(ProductServiceImpl::buildFindProductResponse)
                .toList();
    }

    @Override
    public ApiResponse<?> deleteProductById(int id) {
        productRepository.deleteById(id);

        return ApiResponse.builder()
                .message("Product successfully deleted")
                .build();
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }
}
