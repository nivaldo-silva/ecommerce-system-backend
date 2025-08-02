package io.github.nivaldosilva.ecommerce.services.usecases;

import java.util.List;
import org.springframework.stereotype.Service;
import io.github.nivaldosilva.ecommerce.api.dtos.request.CategoryRequest;
import io.github.nivaldosilva.ecommerce.api.dtos.response.CategoryResponse;
import io.github.nivaldosilva.ecommerce.collections.Category;
import io.github.nivaldosilva.ecommerce.exceptions.CategoryNotFoundException;
import io.github.nivaldosilva.ecommerce.mappers.CategoryMapper;
import io.github.nivaldosilva.ecommerce.repositories.CategoryRepository;
import io.github.nivaldosilva.ecommerce.services.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Override
    public CategoryResponse register(CategoryRequest request) {
        Category category = CategoryMapper.toCategory(request);
        Category savedCategory = repository.save(category);
        return CategoryMapper.toCategoryResponse(savedCategory);
    }

    @Override
    public CategoryResponse findById(String id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> CategoryNotFoundException.withId(id));
        return CategoryMapper.toCategoryResponse(category);
    }

    @Override
    public List<CategoryResponse> findAll(String name) {
        List<Category> categories = repository.findAll();
        return categories.stream().map(CategoryMapper::toCategoryResponse).toList();
    }

    @Override
    public CategoryResponse update(String id, CategoryRequest request) {
        Category existingCategory = repository.findById(id)
                .orElseThrow(() -> CategoryNotFoundException.withId(id));
        if (request.name() != null) {
            existingCategory.setName(request.name());
        }

        if (request.description() != null) {
            existingCategory.setDescription(request.description());
        }

        Category updatedCategory = repository.save(existingCategory);
        return CategoryMapper.toCategoryResponse(updatedCategory);
    }

    @Override
    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw CategoryNotFoundException.withId(id);
        } else {
            repository.deleteById(id);
        }

    }
}