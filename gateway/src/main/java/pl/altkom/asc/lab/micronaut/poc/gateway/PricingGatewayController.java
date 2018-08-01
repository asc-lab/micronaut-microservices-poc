package pl.altkom.asc.lab.micronaut.poc.gateway;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import pl.altkom.asc.lab.micronaut.poc.gateway.client.v1.PricingGatewayClient;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.CalculatePriceCommand;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.CalculatePriceResult;

import javax.inject.Inject;

@Controller("/api/pricing")
public class PricingGatewayController {

    @Inject
    private PricingGatewayClient pricingClient;

    @Post("/price")
    CalculatePriceResult calculatePrice(CalculatePriceCommand cmd) {
        return pricingClient.calculatePrice(cmd);
    }
}
