package io.github.nivaldosilva.ecommerce.api.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CategoryRequest(

        @NotBlank(message = "Name cannot be blank.")
        String name,

        @NotBlank(message = "Description cannot be blank.")
        @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters.")
        String description) {

}
