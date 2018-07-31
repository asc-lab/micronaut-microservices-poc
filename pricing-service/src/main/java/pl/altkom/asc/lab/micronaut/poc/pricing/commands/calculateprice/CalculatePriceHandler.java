package pl.altkom.asc.lab.micronaut.poc.pricing.commands.calculateprice;

import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.pricing.domain.Calculation;
import pl.altkom.asc.lab.micronaut.poc.pricing.domain.Tariff;
import pl.altkom.asc.lab.micronaut.poc.pricing.domain.Tariffs;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.CalculatePriceCommand;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.CalculatePriceResult;

import javax.inject.Singleton;
import java.util.stream.Collectors;

@Singleton
@RequiredArgsConstructor
public class CalculatePriceHandler {
    private final Tariffs tariffs;

    public CalculatePriceResult handle(CalculatePriceCommand calculatePriceCommand) {
        Tariff tariff = tariffs.findByCode(calculatePriceCommand.getProductCode());
        Calculation calculation = tariff.calculatePrice(toCalculation(calculatePriceCommand));
        return resultFromCalculation(calculation);
    }

    private Calculation toCalculation(CalculatePriceCommand calculatePriceCommand) {
        return new Calculation(
                calculatePriceCommand.getProductCode(),
                calculatePriceCommand.getPolicyFrom(),
                calculatePriceCommand.getPolicyTo(),
                calculatePriceCommand.getSelectedCovers(),
                calculatePriceCommand.getAnswers().stream().collect(Collectors.toMap(a -> a.getQuestionCode(), a -> a.getAnswer()))
        );
    }

    private CalculatePriceResult resultFromCalculation(Calculation calculation) {
        return new CalculatePriceResult(
                calculation.getTotalPremium(),
                calculation.getCovers().entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().getPrice()))
        );
    }
}
