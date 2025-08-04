package io.github.nivaldosilva.ecommerce.repositories;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import io.github.nivaldosilva.ecommerce.collections.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByNameContainingIgnoreCase(String name);

    boolean existsByBarcode(String barcode);

}
