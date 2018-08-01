package pl.altkom.asc.lab.micronaut.poc.gateway.client.v1;

import io.micronaut.http.client.Client;
import io.micronaut.retry.annotation.Retryable;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.CalculatePriceCommand;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.CalculatePriceResult;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.PricingOperations;

@Client(id = "pricing-service", path = "/pricing")
@Retryable(attempts = "2", delay = "2s")
public interface PricingGatewayClient extends PricingOperations {

    @Override
    CalculatePriceResult calculatePrice(CalculatePriceCommand cmd);
}
