package io.github.nivaldosilva.ecommerce.api.dtos.request;

import java.math.BigDecimal;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProductRequest(


        @NotBlank(message = "Name cannot be blank.")
        @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters.")
        String name,

        @NotBlank(message = "Description cannot be blank.")
        @Size(min = 5, max = 1000, message = "Description must be between 10 and 1000 characters.")
        String description,

        @NotEmpty(message = "Categories cannot be empty.")
        List<String> categories,

        @NotBlank(message = "Barcode cannot be blank.")
        String barcode,

        @NotNull(message = "Price cannot be null.")
        @DecimalMin(value = "0.0", message = "The price of the product cannot be negative.")
        BigDecimal price,

        @NotNull(message = "Quantity cannot be null.")
        @Min(value = 0, message = "The quantity of the product cannot be negative.")
        Integer quantity) {

}
