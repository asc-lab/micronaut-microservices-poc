package pl.altkom.asc.lab.micronaut.poc.gateway.client.v1;

import documents.service.api.DocumentsOperations;
import documents.service.api.queries.finddocuments.FindDocumentsResult;
import io.micronaut.http.client.Client;
import io.micronaut.retry.annotation.Retryable;
import org.jetbrains.annotations.NotNull;

@Client(id = "documents-service", path = "/documents")
@Retryable(attempts = "2", delay = "2s")
public interface DocumentsGatewayClient extends DocumentsOperations {

    @Override
    FindDocumentsResult find(String policyNumber);
}
