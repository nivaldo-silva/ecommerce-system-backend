package io.github.nivaldosilva.ecommerce.api.controllers;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.nivaldosilva.ecommerce.api.dtos.request.CategoryRequest;
import io.github.nivaldosilva.ecommerce.api.dtos.response.CategoryResponse;
import io.github.nivaldosilva.ecommerce.services.interfaces.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> registerCategory(@RequestBody @Valid CategoryRequest request) {
        logger.info("Creating category");
        CategoryResponse response = categoryService.register(request);
        logger.info("Category created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll() {
        logger.info("Finding all categories");
        List<CategoryResponse> response = categoryService.findAll(null);
        logger.info("List of categories found successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable String id) {
        logger.info("Finding category by id: {}", id);
        CategoryResponse response = categoryService.findById(id);
        logger.info("Category found successfully");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable String id, @RequestBody @Valid CategoryRequest request) {
        logger.info("Updating category with ID: {}", id);
        CategoryResponse response = categoryService.update(id, request);
        logger.info("Category updated successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        logger.info("Deleting category with ID: {}", id);
        categoryService.delete(id);
        logger.info("Category deleted successfully");
        return ResponseEntity.noContent().build();
    }
}
