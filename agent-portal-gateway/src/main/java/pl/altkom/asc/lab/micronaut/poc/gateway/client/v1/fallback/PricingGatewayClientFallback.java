package pl.altkom.asc.lab.micronaut.poc.gateway.client.v1.fallback;

import io.micronaut.retry.annotation.Fallback;
import pl.altkom.asc.lab.micronaut.poc.gateway.client.v1.PricingGatewayClient;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.CalculatePriceCommand;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.CalculatePriceResult;

import javax.inject.Singleton;

@Singleton
@Fallback
public class PricingGatewayClientFallback implements PricingGatewayClient {

    @Override
    public CalculatePriceResult calculatePrice(CalculatePriceCommand cmd) {
        return CalculatePriceResult.failure();
    }
}
