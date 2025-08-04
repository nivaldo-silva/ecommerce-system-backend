package io.github.nivaldosilva.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateBarcodeException extends RuntimeException {

    public DuplicateBarcodeException(String message) {
        super(message);
    }

}
