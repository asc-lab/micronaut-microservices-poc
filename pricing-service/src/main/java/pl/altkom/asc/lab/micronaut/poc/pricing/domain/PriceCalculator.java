package pl.altkom.asc.lab.micronaut.poc.pricing.domain;

import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.ServicePriceDto;

public interface PriceCalculator {
    ServicePriceDto findPriceForService(String serviceCode);
}
