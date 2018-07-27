package pl.altkom.asc.lab.micronaut.poc.pricing;

import io.micronaut.http.annotation.Controller;
import io.micronaut.validation.Validated;
import pl.altkom.asc.lab.micronaut.poc.pricing.domain.PriceCalculator;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.PricingOperations;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.ServicePriceDto;

import javax.validation.constraints.NotBlank;

@Controller("/pricing")
@Validated
public class PricingController implements PricingOperations {

    private final PriceCalculator priceCalculator;

    public PricingController(PriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }

    @Override
    public ServicePriceDto getPriceForService(@NotBlank String serviceCode) {
        return priceCalculator.findPriceForService(serviceCode);
    }
}
