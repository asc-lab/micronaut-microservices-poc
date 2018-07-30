package pl.altkom.asc.lab.micronaut.poc.pricing;

import io.micronaut.http.client.Client;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.PricingOperations;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.ServicePriceDto;

import javax.validation.constraints.NotBlank;

@Client(id = "/pricing-service", path = "/pricing")
public interface PricingTestClient extends PricingOperations {

    @Override
    ServicePriceDto getPriceForService(@NotBlank String serviceCode);
}
