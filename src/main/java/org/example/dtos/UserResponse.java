package org.example.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter @Builder @JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    private String firstName;
    private String lastName;
    private String email;
}
