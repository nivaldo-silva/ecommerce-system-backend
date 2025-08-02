package io.github.nivaldosilva.ecommerce.collections;

import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import io.github.nivaldosilva.ecommerce.collections.vo.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "clients")
@Data
@Builder
@NoArgsConstructor
public class Client {

    @Id
    private String id;

    @NotBlank(message = "Name cannot be blank.")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters.")
    private String name;

    @Indexed(unique = true)
    @NotBlank(message = "CPF cannot be blank.")
    @Pattern(regexp = "^\\d{11}$", message = "CPF must contain exactly 11 numeric digits.")
    private String cpf;

    @Indexed(unique = true)
    @NotBlank(message = "Email cannot be blank.")
    @Email(message = "Invalid email format.")
    @Size(max = 100, message = "Email must have a maximum of 100 characters.")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\(?\\d{2}\\)?[\\s-]?\\d{4,5}[\\s-]?\\d{4}$", message = "Invalid phone format")
    private String telephone;

    @Valid
    private Address address;

    @NotNull(message = "Active status cannot be null.")
    private Boolean active;

    public Client(String id, String name, String cpf, String email, String telephone, Address address,
            Boolean active) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
        this.active = true;
    }
}