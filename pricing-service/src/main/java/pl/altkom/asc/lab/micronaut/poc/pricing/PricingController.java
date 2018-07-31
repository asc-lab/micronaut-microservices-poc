package pl.altkom.asc.lab.micronaut.poc.pricing;

import io.micronaut.http.annotation.Controller;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.pricing.commands.calculateprice.CalculatePriceHandler;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.CalculatePriceCommand;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.CalculatePriceResult;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.PricingOperations;

import javax.validation.constraints.NotBlank;

@Controller("/pricing")
@Validated
@RequiredArgsConstructor
public class PricingController implements PricingOperations {
    private final CalculatePriceHandler calculatePriceHandler;

    @Override
    public CalculatePriceResult calculatePrice(@NotBlank CalculatePriceCommand cmd) {
        return calculatePriceHandler.handle(cmd);
    }
}
