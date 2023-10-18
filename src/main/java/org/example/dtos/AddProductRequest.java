package org.example.dtos;

import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AddProductRequest {
    private String productName;
    private double price;
    @Nullable
    private String description;
}
