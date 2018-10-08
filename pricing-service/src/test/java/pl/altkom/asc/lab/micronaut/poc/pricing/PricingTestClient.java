package pl.altkom.asc.lab.micronaut.poc.pricing;

import io.micronaut.http.client.annotation.Client;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.PricingOperations;

@Client(id = "/pricing-service", path = "/pricing")
public interface PricingTestClient extends PricingOperations {
}
