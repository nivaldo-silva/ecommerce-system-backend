package io.github.nivaldosilva.ecommerce.api.controllers;

import io.github.nivaldosilva.ecommerce.api.dtos.request.ProductRequest;
import io.github.nivaldosilva.ecommerce.api.dtos.response.ProductResponse;
import io.github.nivaldosilva.ecommerce.services.interfaces.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> register(@RequestBody @Valid ProductRequest request) {
        logger.info("Creating product");
        ProductResponse response = productService.register(request);
        logger.info("Product created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable String id) {
        logger.info("Finding product by id: {}", id);
        ProductResponse response = productService.findById(id);
        logger.info("Product found successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll(@RequestParam(required = false) String name) {
        logger.info("Finding all products");
        List<ProductResponse> products = productService.findAll(name);
        logger.info("List of products found successfully");
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable String id, @RequestBody ProductRequest request) {
        logger.info("Updating product with ID: {}", id);
        ProductResponse response = productService.update(id, request);
        logger.info("Product updated successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        logger.info("Deleting product with ID: {}", id);
        productService.delete(id);
        logger.info("Product deleted successfully");
        return ResponseEntity.noContent().build();
    }
}
