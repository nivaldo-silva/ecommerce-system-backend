package io.github.nivaldosilva.ecommerce.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CpfAlreadyExistsException extends RuntimeException {

    public CpfAlreadyExistsException(String cpf) {
        super("Erro: Já existe um cliente cadastrado com o CPF " + cpf);
    }

}
