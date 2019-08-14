package pl.altkom.asc.lab.micronaut.poc.pricing;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.commands.calculateprice.CalculatePriceCommand;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.commands.calculateprice.CalculatePriceResult;

import javax.validation.constraints.NotNull;

@Client(id = "/pricing-service", path = "/pricing")
public interface PricingTestClient {

    @Post("/calculate")
    CalculatePriceResult calculatePrice(@Body @NotNull CalculatePriceCommand cmd);
}

