package io.github.nivaldosilva.ecommerce.api.dtos.response;

import java.math.BigDecimal;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProductResponse(

        String id,
        String name,
        String description,
        List<String> categories,
        String barcode,
        BigDecimal price,
        Integer quantity) {

}
