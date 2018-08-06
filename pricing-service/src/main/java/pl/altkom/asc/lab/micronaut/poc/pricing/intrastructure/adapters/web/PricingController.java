package pl.altkom.asc.lab.micronaut.poc.pricing.intrastructure.adapters.web;

import io.micronaut.http.annotation.Controller;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.altkom.asc.lab.micronaut.poc.pricing.commands.CalculatePriceHandler;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.CalculatePriceCommand;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.CalculatePriceResult;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.PricingOperations;

@Controller("/pricing")
@Validated
@RequiredArgsConstructor
@Slf4j
public class PricingController implements PricingOperations {

    private final CalculatePriceHandler calculatePriceHandler;

    @Override
    public CalculatePriceResult calculatePrice(CalculatePriceCommand cmd) {
        return calculatePriceHandler.handle(cmd);
    }
}
