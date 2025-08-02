package io.github.nivaldosilva.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends RuntimeException {

    private CategoryNotFoundException(String message) {
        super(message);
    }

    public static CategoryNotFoundException withId(String id) {
        return new CategoryNotFoundException("Category not found with id: " + id);
    }

}
