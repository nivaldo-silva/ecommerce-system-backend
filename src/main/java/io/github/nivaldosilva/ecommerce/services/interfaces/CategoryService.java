package io.github.nivaldosilva.ecommerce.services.interfaces;

import java.util.List;
import io.github.nivaldosilva.ecommerce.api.dtos.request.CategoryRequest;
import io.github.nivaldosilva.ecommerce.api.dtos.response.CategoryResponse;

public interface CategoryService {

    CategoryResponse register(CategoryRequest request);

    CategoryResponse findById(String id);

    List<CategoryResponse> findAll(String name);

    CategoryResponse update(String id, CategoryRequest request);

    void delete(String id);

}
