package io.github.nivaldosilva.ecommerce.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import io.github.nivaldosilva.ecommerce.collections.Client;

public interface ClientRepository extends MongoRepository<Client, String> {

    Optional<Client> findByCpf(String cpf);

    Optional<Client> findByEmail(String email);

    List<Client> findByNameContainingIgnoreCase(String name);

}
