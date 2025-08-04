package io.github.nivaldosilva.ecommerce.services.interfaces;

import java.util.List;
import io.github.nivaldosilva.ecommerce.api.dtos.request.ProductRequest;
import io.github.nivaldosilva.ecommerce.api.dtos.response.ProductResponse;

public interface ProductService {

    ProductResponse register(ProductRequest request);

    ProductResponse findById(String id);

    List<ProductResponse> findAll(String name);

    ProductResponse update(String id, ProductRequest request);

    void delete(String id);

}
