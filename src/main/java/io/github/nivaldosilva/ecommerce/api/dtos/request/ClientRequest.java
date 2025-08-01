package io.github.nivaldosilva.ecommerce.api.dtos.request;

import io.github.nivaldosilva.ecommerce.collections.vo.Address;
import lombok.Builder;

@Builder
public record ClientRequest(

        String name,
        String cpf,
        String email,
        String telephone,
        Address address) {

}