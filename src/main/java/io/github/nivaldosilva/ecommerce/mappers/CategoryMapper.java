package io.github.nivaldosilva.ecommerce.mappers;

import io.github.nivaldosilva.ecommerce.api.dtos.request.CategoryRequest;
import io.github.nivaldosilva.ecommerce.api.dtos.response.CategoryResponse;
import io.github.nivaldosilva.ecommerce.collections.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public Category toCategory(CategoryRequest request) {
        if (request == null) {
            return null;
        } else {
            return Category.builder()
                    .name(request.name())
                    .description(request.description())
                    .build();

        }
    }

    public CategoryResponse toCategoryResponse(Category category) {
        if (category == null) {
            return null;
        } else {
            return CategoryResponse.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .description(category.getDescription())
                    .build();
        }
    }

}
