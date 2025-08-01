package io.github.nivaldosilva.ecommerce.api.dtos.request;

import lombok.Builder;

@Builder
public record AddressRequest(

                String zipCode,
                String street,
                String number,
                String complement,
                String neighborhood,
                String city,
                String uf){

}