package pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.commands.calculateprice.CalculatePriceCommand;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.commands.calculateprice.CalculatePriceResult;

import javax.validation.constraints.NotNull;

public interface PricingOperations {

    @Post("/calculate")
    CalculatePriceResult calculatePrice(@Body @NotNull CalculatePriceCommand cmd);
}
