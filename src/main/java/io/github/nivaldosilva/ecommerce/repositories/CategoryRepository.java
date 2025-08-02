package io.github.nivaldosilva.ecommerce.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import io.github.nivaldosilva.ecommerce.collections.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {

}
