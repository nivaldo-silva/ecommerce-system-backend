package io.github.nivaldosilva.ecommerce.collections;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "products")
@Data
@Builder
@NoArgsConstructor
public class Product {

    @Id
    private String id;

    @NotBlank(message = "Name cannot be blank.")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters.")
    private String name;

    @NotBlank(message = "Description cannot be blank.")
    @Size(min = 5, max = 1000, message = "Description must be between 10 and 1000 characters.")
    private String description;

    @NotEmpty(message = "Categories cannot be empty.")
    private List<String> categories;

    @NotBlank(message = "Barcode cannot be blank.")
    private String barcode;

    @NotNull(message = "Price cannot be null.")
    @DecimalMin(value = "0.0", message = "The price of the product cannot be negative.")
    private BigDecimal price;

    @NotNull(message = "Quantity cannot be null.")
    @Min(value = 0, message = "The quantity of the product cannot be negative.")
    private Integer quantity;

    @NotNull(message = "Active status cannot be null.")
    private boolean active;
    

    public Product(String id, String name, String description, List<String> categories, String barcode,
            BigDecimal price,
            Integer quantity, boolean active) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.categories = categories;
        this.barcode = barcode;
        this.price = price;
        this.quantity = quantity;
        this.active = active;
    }

}
