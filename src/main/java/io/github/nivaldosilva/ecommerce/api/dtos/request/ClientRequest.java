package io.github.nivaldosilva.ecommerce.api.dtos.request;

import io.github.nivaldosilva.ecommerce.collections.vo.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record ClientRequest(

@NotBlank(message = "Name cannot be blank.") 
@Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters.") 
String name,

@NotBlank(message = "CPF cannot be blank.")
@Size(min = 11, max = 11, message = "CPF must be 11 characters.")
String cpf,

@NotBlank(message = "Email cannot be blank.")
@Email(message = "Invalid email format.")
@Size(min = 5, max = 100, message = "Email must be between 5 and 100 characters.")
String email,

@NotBlank(message = "Phone number is required")
@Pattern(regexp = "^\\(?\\d{2}\\)?[\\s-]?\\d{4,5}[\\s-]?\\d{4}$", message = "Invalid phone format")
String telephone,
               
@Valid
Address address) {

}