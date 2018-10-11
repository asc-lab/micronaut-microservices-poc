package pl.altkom.asc.lab.micronaut.poc.gateway.client.v1;

import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Retryable;
import io.reactivex.Maybe;
import pl.altkom.asc.lab.micronaut.poc.product.service.api.v1.ProductDto;
import pl.altkom.asc.lab.micronaut.poc.product.service.api.v1.ProductOperations;

@Client("http://product-service/products")
@Retryable(attempts = "2", delay = "2s")
public interface ProductGatewayClient extends ProductOperations {

    @Override
    Maybe<ProductDto> get(String productCode);
}
