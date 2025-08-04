package io.github.nivaldosilva.ecommerce.api.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.nivaldosilva.ecommerce.collections.vo.Address;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ClientResponse(

                String id,
                String name,
                String cpf,
                String email,
                String telephone,
                Address address) {

}