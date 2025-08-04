package io.github.nivaldosilva.ecommerce.api.dtos.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record AddressRequest(

    @NotBlank(message = "The zip code cannot be blank.")
    @Pattern(regexp = "^\\d{5}-\\d{3}$|^\\d{8}$", message = "Invalid zip code format. Use XXXXX-XXX or XXXXXXXX.")
    String zipCode,

    @NotBlank(message = "The street cannot be blank.")
    @Size(max = 100, message = "The street must have a maximum of 100 characters.")
    String street,
    
    @NotBlank(message = "The number cannot be blank.")
    @Size(max = 10, message = "The number must have a maximum of 10 characters.")            
    String number,

    @Size(max = 50, message = "The complement must have a maximum of 50 characters.")
    String complement,

    @NotBlank(message = "The neighborhood cannot be blank.")
    @Size(max = 100, message = "The neighborhood must have a maximum of 100 characters.")
    String neighborhood,

    @NotBlank(message = "The city cannot be blank.")
    @Size(max = 100, message = "The city must have a maximum of 100 characters.")
    String city,

    @NotBlank(message = "The state cannot be blank.")
    @Size(min = 2, max = 2, message = "The state must have 2 characters (e.g., UF in Brazil).")
    @Pattern(regexp = "^[A-Z]{2}$", message = "The state must be a valid UF (e.g., SP, RJ).")
    String uf){

}