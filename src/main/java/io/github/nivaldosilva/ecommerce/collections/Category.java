package io.github.nivaldosilva.ecommerce.collections;

import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "categories")
@Data
@Builder
@NoArgsConstructor
public class Category {

    @Id
    private String id;

    @NotBlank(message = "Name cannot be blank.")
    private String name;

    @NotBlank(message = "Description cannot be blank.")
    @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters.")
    private String description;

    public Category(String id, String name, String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
    }

}
