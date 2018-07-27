package pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1;

import io.micronaut.http.annotation.Get;

import javax.validation.constraints.NotBlank;

public interface PricingOperations {

    @Get("/{serviceCode}")
    ServicePriceDto getPriceForService(@NotBlank String serviceCode);
}
