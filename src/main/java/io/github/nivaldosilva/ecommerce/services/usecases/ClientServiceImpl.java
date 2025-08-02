package io.github.nivaldosilva.ecommerce.services.usecases;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import io.github.nivaldosilva.ecommerce.api.dtos.request.ClientRequest;
import io.github.nivaldosilva.ecommerce.api.dtos.response.ClientResponse;
import io.github.nivaldosilva.ecommerce.collections.Client;
import io.github.nivaldosilva.ecommerce.exceptions.ClientNotFoundException;
import io.github.nivaldosilva.ecommerce.exceptions.CpfAlreadyExistsException;
import io.github.nivaldosilva.ecommerce.exceptions.EmailAlreadyExistsException;
import io.github.nivaldosilva.ecommerce.mappers.ClientMapper;
import io.github.nivaldosilva.ecommerce.repositories.ClientRepository;
import io.github.nivaldosilva.ecommerce.services.interfaces.ClientService;
import org.springframework.util.StringUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    @Override
    public ClientResponse register(ClientRequest request) {
        Client client = ClientMapper.toClient(request);
        validateUniqueness(client.getCpf(), client.getEmail(), null);
        Client savedClient = repository.save(client);
        return ClientMapper.toClientResponse(savedClient);
    }

    @Override
    public ClientResponse findById(String id) {
        Client client = findByIdOrThrow(id);
        return ClientMapper.toClientResponse(client);
    }

    @Override
    public List<ClientResponse> findAll(String name) {
        List<Client> clients = StringUtils.hasText(name)
                ? repository.findByNameContainingIgnoreCase(name)
                : repository.findAll();
        return clients.stream().map(ClientMapper::toClientResponse).toList();
    }

    @Override
    public ClientResponse update(String id, ClientRequest request) {
        if (!StringUtils.hasText(id)) {
            throw new IllegalArgumentException("The client ID for update cannot be null or empty.");
        }

        Client existingClient = findByIdOrThrow(id);
        validateUniqueness(request.cpf(), request.email(), id);

        updateFields(existingClient, request);
        Client updatedClient = repository.save(existingClient);
        return ClientMapper.toClientResponse(updatedClient);
    }

    @Override
    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new ClientNotFoundException("Client with ID " + id + " not found for deletion.");
        }
        repository.deleteById(id);
    }

    private Client findByIdOrThrow(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client with ID " + id + " not found."));
    }

    private void validateUniqueness(String cpf, String email, String idToExclude) {
        validateCpfUniqueness(cpf, idToExclude);
        validateEmailUniqueness(email, idToExclude);
    }

    private void validateCpfUniqueness(String cpf, String idToExclude) {
        Optional<Client> clientWithCpf = repository.findByCpf(cpf);
        if (clientWithCpf.isPresent() && !clientWithCpf.get().getId().equals(idToExclude)) {
            throw new CpfAlreadyExistsException(cpf);
        }
    }

    private void validateEmailUniqueness(String email, String idToExclude) {
        Optional<Client> clientWithEmail = repository.findByEmail(email);
        if (clientWithEmail.isPresent() && !clientWithEmail.get().getId().equals(idToExclude)) {
            throw new EmailAlreadyExistsException(email);
        }
    }

    private void updateFields(Client existingClient, ClientRequest request) {
        Optional.ofNullable(request.name())
                .filter(StringUtils::hasText)
                .ifPresent(existingClient::setName);

        Optional.ofNullable(request.cpf())
                .filter(StringUtils::hasText)
                .ifPresent(existingClient::setCpf);

        Optional.ofNullable(request.email())
                .filter(StringUtils::hasText)
                .ifPresent(existingClient::setEmail);

        Optional.ofNullable(request.telephone())
                .filter(StringUtils::hasText)
                .ifPresent(existingClient::setTelephone);

        Optional.ofNullable(request.address())
                .ifPresent(existingClient::setAddress);
    }
}