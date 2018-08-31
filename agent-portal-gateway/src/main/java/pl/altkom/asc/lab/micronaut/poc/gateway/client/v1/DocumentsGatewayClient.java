package pl.altkom.asc.lab.micronaut.poc.gateway.client.v1;

import documents.service.api.DocumentsOperations;
import io.micronaut.http.client.Client;
import io.micronaut.retry.annotation.Retryable;

@Client(id = "documents-service", path = "/documents")
@Retryable(attempts = "2", delay = "2s")
public interface DocumentsGatewayClient extends DocumentsOperations {

}
