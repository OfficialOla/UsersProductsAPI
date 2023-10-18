package org.example.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor@Builder@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private String message;
    private T data;
}
