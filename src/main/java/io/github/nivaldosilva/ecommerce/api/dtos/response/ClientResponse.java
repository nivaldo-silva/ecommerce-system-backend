package io.github.nivaldosilva.ecommerce.api.dtos.response;

import io.github.nivaldosilva.ecommerce.collections.vo.Address;
import lombok.Builder;

@Builder
public record ClientResponse(

                String id,
                String name,
                String cpf,
                String email,
                String telephone,
                Address address) {

}