package io.github.nivaldosilva.ecommerce.api.controllers;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.nivaldosilva.ecommerce.api.dtos.request.ClientRequest;
import io.github.nivaldosilva.ecommerce.api.dtos.response.ClientResponse;
import io.github.nivaldosilva.ecommerce.services.interfaces.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/clients")
@RequiredArgsConstructor
public class ClientController {

  private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
  private final ClientService clientService;

  @PostMapping
  public ResponseEntity<ClientResponse> registerClient(@RequestBody @Valid ClientRequest request) {
    logger.info("Creating client");
    ClientResponse response = clientService.register(request);
    logger.info("Client created successfully");
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping
  public ResponseEntity<List<ClientResponse>> findAll(
      @RequestParam(required = false, defaultValue = "") String name) {
    logger.info("Finding all clients");
    List<ClientResponse> response = clientService.findAll(name);
    logger.info("List of clients found successfully");
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ClientResponse> findById(@PathVariable String id) {
    logger.info("Finding client by id: {}", id);
    ClientResponse response = clientService.findById(id);
    logger.info("Client found successfully");
    return ResponseEntity.ok(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ClientResponse> update(
      @PathVariable String id,
      @RequestBody @Valid ClientRequest request) {
    logger.info("Updating client with ID: {}", id);
    ClientResponse response = clientService.update(id, request);
    logger.info("Client updated successfully");
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    logger.info("Deleting client with ID: {}", id);
    clientService.delete(id);
    logger.info("Client deleted successfully");
    return ResponseEntity.noContent().build();
  }
}