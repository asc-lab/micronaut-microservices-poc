package pl.altkom.asc.lab.micronaut.poc.policy.client;

import io.micronaut.http.client.Client;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.PricingOperations;

@Client(id = "/pricing-service", path = "/pricing")
public interface PricingClient extends PricingOperations {
}
