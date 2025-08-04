package io.github.nivaldosilva.ecommerce.mappers;

import java.util.Optional;
import io.github.nivaldosilva.ecommerce.api.dtos.request.ProductRequest;
import io.github.nivaldosilva.ecommerce.api.dtos.response.ProductResponse;
import io.github.nivaldosilva.ecommerce.collections.Product;
import org.springframework.util.StringUtils;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductMapper {

    public Product toProduct(ProductRequest request) {
        if (request == null) {
            return null;
        } else {
            return Product.builder()
                    .name(request.name())
                    .description(request.description())
                    .categories(request.categories())
                    .barcode(request.barcode())
                    .price(request.price())
                    .quantity(request.quantity())
                    .active(true)
                    .build();
        }
    }

    public ProductResponse toProductResponse(Product product) {
        if (product == null) {
            return null;
        } else {
            return ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .categories(product.getCategories())
                    .barcode(product.getBarcode())
                    .price(product.getPrice())
                    .quantity(product.getQuantity())
                    .build();
        }
    }

    public static void updateProductFromRequest(Product existingProduct, ProductRequest request) {
        Optional.ofNullable(request.name())
                .filter(StringUtils::hasText)
                .ifPresent(existingProduct::setName);

        Optional.ofNullable(request.description())
                .ifPresent(existingProduct::setDescription);

        Optional.ofNullable(request.barcode())
                .ifPresent(existingProduct::setBarcode);

        Optional.ofNullable(request.categories())
                .ifPresent(existingProduct::setCategories);

        Optional.ofNullable(request.price())
                .ifPresent(existingProduct::setPrice);

        Optional.ofNullable(request.quantity())
                .ifPresent(existingProduct::setQuantity);
    }

}
