package pl.altkom.asc.lab.micronaut.poc.pricing;

import io.micronaut.http.client.annotation.Client;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.PricingOperations;

@Client("http://pricing-service/pricing")
public interface PricingTestClient extends PricingOperations {
}
