package io.github.nivaldosilva.ecommerce.api.dtos.response;

import lombok.Builder;

@Builder
public record CategoryResponse(
        
        String id,
        String name,
        String description) {

}
