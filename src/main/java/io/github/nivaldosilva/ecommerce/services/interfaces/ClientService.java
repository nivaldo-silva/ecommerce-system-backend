package io.github.nivaldosilva.ecommerce.services.interfaces;

import java.util.List;

import io.github.nivaldosilva.ecommerce.api.dtos.request.ClientRequest;
import io.github.nivaldosilva.ecommerce.api.dtos.response.ClientResponse;

public interface ClientService {

    ClientResponse register(ClientRequest request);

    ClientResponse findById(String id);

    List<ClientResponse> findAll(String name);

    ClientResponse update(String id, ClientRequest request);

    void delete(String id);
}