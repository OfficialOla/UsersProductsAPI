package org.example.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter@Builder@JsonInclude(JsonInclude.Include.NON_NULL)
public class ViewProductResponse {
    private int id;
    private String productName;
    private double price;
    private String description;
}
