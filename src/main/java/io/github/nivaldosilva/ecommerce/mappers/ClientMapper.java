package io.github.nivaldosilva.ecommerce.mappers;

import io.github.nivaldosilva.ecommerce.api.dtos.request.ClientRequest;
import io.github.nivaldosilva.ecommerce.api.dtos.response.ClientResponse;
import io.github.nivaldosilva.ecommerce.collections.Client;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ClientMapper {

    public Client toClient(ClientRequest request) {
        if (request == null) {
            return null;
        }

        return Client.builder()
                .name(request.name())
                .cpf(request.cpf())
                .email(request.email())
                .telephone(request.telephone())
                .address(request.address())
                .active(true)
                .build();
    }

    public ClientResponse toClientResponse(Client client) {
        if (client == null) {
            return null;
        }

        return ClientResponse.builder()
                .id(client.getId())
                .name(client.getName())
                .cpf(client.getCpf())
                .email(client.getEmail())
                .telephone(client.getTelephone())
                .address(client.getAddress())
                .build();
    }

}