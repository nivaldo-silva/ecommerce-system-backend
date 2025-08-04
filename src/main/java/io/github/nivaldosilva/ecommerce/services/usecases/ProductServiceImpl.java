package io.github.nivaldosilva.ecommerce.services.usecases;

import io.github.nivaldosilva.ecommerce.api.dtos.request.ProductRequest;
import io.github.nivaldosilva.ecommerce.api.dtos.response.ProductResponse;
import io.github.nivaldosilva.ecommerce.collections.Product;
import io.github.nivaldosilva.ecommerce.exceptions.DuplicateBarcodeException;
import io.github.nivaldosilva.ecommerce.exceptions.ProductNotFoundException;
import io.github.nivaldosilva.ecommerce.exceptions.ValidationException;
import io.github.nivaldosilva.ecommerce.mappers.ProductMapper;
import io.github.nivaldosilva.ecommerce.repositories.CategoryRepository;
import io.github.nivaldosilva.ecommerce.repositories.ProductRepository;
import io.github.nivaldosilva.ecommerce.services.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductResponse register(ProductRequest request) {
        Optional.ofNullable(request.barcode())
                .filter(StringUtils::hasText)
                .ifPresent(barcode -> {
                    if (productRepository.existsByBarcode(barcode)) {
                        throw new DuplicateBarcodeException("Product with barcode already exists.");
                    }
                });

        Optional.ofNullable(request.categories())
                .filter(categories -> !categories.isEmpty())
                .ifPresent(categories -> {
                    if (categoryRepository.findAllById(categories).size() != categories.size()) {
                        throw new ValidationException("All categories must exist.");
                    }
                });

        Product product = ProductMapper.toProduct(request);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.toProductResponse(savedProduct);
    }

    @Override
    public ProductResponse findById(String id) {
        if (!StringUtils.hasText(id)) {
            throw new ValidationException("Product ID cannot be null or empty.");
        }

        return productRepository.findById(id)
                .map(ProductMapper::toProductResponse)
                .orElseThrow(() -> new ProductNotFoundException("Product not found."));
    }

    @Override
    public List<ProductResponse> findAll(String name) {
        List<Product> products = StringUtils.hasText(name)
                ? productRepository.findByNameContainingIgnoreCase(name.trim())
                : productRepository.findAll();

        return products.stream()
                .map(ProductMapper::toProductResponse)
                .toList();
    }

    @Override
    public ProductResponse update(String id, ProductRequest request) {
        if (!StringUtils.hasText(id)) {
            throw new ValidationException("Product ID cannot be null or empty.");
        }

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found."));

        Optional.ofNullable(request.barcode())
                .filter(StringUtils::hasText)
                .ifPresent(newBarcode -> {
                    if (!newBarcode.equals(existingProduct.getBarcode()) &&
                            productRepository.existsByBarcode(newBarcode)) {
                        throw new DuplicateBarcodeException("Product with barcode already exists.");
                    }
                });

        Optional.ofNullable(request.categories())
                .filter(categories -> !categories.isEmpty())
                .ifPresent(categories -> {
                    if (categoryRepository.findAllById(categories).size() != categories.size()) {
                        throw new ValidationException("All categories must exist.");
                    }
                });

        ProductMapper.updateProductFromRequest(existingProduct, request);

        Product updatedProduct = productRepository.save(existingProduct);
        return ProductMapper.toProductResponse(updatedProduct);
    }

    
    @Override
    public void delete(String id) {
        if (!StringUtils.hasText(id)) {
            throw new ValidationException("Product ID cannot be null or empty.");
        }

        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found for deletion.");
        }

        productRepository.deleteById(id);
    }
}