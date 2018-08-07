package pl.altkom.asc.lab.micronaut.poc.pricing.commands;

import io.micronaut.spring.tx.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.pricing.domain.Calculation;
import pl.altkom.asc.lab.micronaut.poc.pricing.domain.Tariff;
import pl.altkom.asc.lab.micronaut.poc.pricing.domain.Tariffs;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.commands.calculateprice.CalculatePriceCommand;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.commands.calculateprice.CalculatePriceResult;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.commands.calculateprice.dto.QuestionAnswer;

import javax.inject.Singleton;
import java.util.Map;
import java.util.stream.Collectors;

@Singleton
@RequiredArgsConstructor
public class CalculatePriceHandler {

    private final Tariffs tariffs;

    @Transactional(readOnly = true)
    public CalculatePriceResult handle(CalculatePriceCommand calculatePriceCommand) {
        Tariff tariff = tariffs.getByCode(calculatePriceCommand.getProductCode());
        Calculation calculation = tariff.calculatePrice(toCalculation(calculatePriceCommand));

        return resultFromCalculation(calculation);
    }

    private Calculation toCalculation(CalculatePriceCommand calculatePriceCommand) {
        return new Calculation(
                calculatePriceCommand.getProductCode(),
                calculatePriceCommand.getPolicyFrom(),
                calculatePriceCommand.getPolicyTo(),
                calculatePriceCommand.getSelectedCovers(),
                calculatePriceCommand.getAnswers().stream()
                        .collect(Collectors.toMap(QuestionAnswer::getQuestionCode, QuestionAnswer::getAnswer))
        );
    }

    private CalculatePriceResult resultFromCalculation(Calculation calculation) {
        return new CalculatePriceResult(
                calculation.getTotalPremium(),
                calculation.getCovers().entrySet().stream()
                        .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getPrice()))
        );
    }
}
