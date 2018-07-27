package pl.altkom.asc.lab.micronaut.poc.policy;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.policy.client.PricingClient;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.ServicePriceDto;

import javax.validation.constraints.NotBlank;

@Validated
@Controller("/pricing-test")
@RequiredArgsConstructor
public class PricingTestController {

    private final PricingClient pricingClient;

    @Get("/{serviceCode}")
    ServicePriceDto getPriceForService(@NotBlank String serviceCode) {
        return pricingClient.getPriceForService(serviceCode);
    }
}
