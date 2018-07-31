package pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

import javax.validation.constraints.NotBlank;

public interface PricingOperations {

    @Post("/calculate")
    CalculatePriceResult calculatePrice(@Body CalculatePriceCommand calculatePriceCommand);
}
