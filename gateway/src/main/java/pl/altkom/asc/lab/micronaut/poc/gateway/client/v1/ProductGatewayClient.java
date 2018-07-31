package pl.altkom.asc.lab.micronaut.poc.gateway.client.v1;

import io.micronaut.http.client.Client;
import io.micronaut.retry.annotation.Retryable;
import pl.altkom.asc.lab.micronaut.poc.product.service.api.v1.ProductOperations;

@Client(id = "product-service", path = "/api/product")
@Retryable(attempts = "5", delay = "2s")
public interface ProductGatewayClient extends ProductOperations {
}
