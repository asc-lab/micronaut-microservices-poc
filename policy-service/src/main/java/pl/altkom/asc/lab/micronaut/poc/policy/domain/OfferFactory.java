package pl.altkom.asc.lab.micronaut.poc.policy.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.CalculatePriceCommand;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.CalculatePriceResult;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.QuestionAnswer;

public class OfferFactory {
    public static Offer offerFromPrice(CalculatePriceCommand calcPriceCmd, CalculatePriceResult calcPriceResult) {
        return  new Offer(
                null, 
                UUID.randomUUID().toString(), 
                calcPriceCmd.getProductCode(), 
                calcPriceCmd.getPolicyFrom(), 
                calcPriceCmd.getPolicyTo(), 
                constructAnswers(calcPriceCmd.getAnswers()), 
                calcPriceResult.getTotalPrice(), 
                calcPriceResult.getCoversPrices(), 
                OfferStatus.NEW,
                LocalDate.now());
    }

    private static Map<String, String> constructAnswers(List<QuestionAnswer> answers) {
        return answers.stream()
                .collect(Collectors.toMap(a -> a.getQuestionCode(), a -> a.getAnswer()!=null ? a.getAnswer().toString() : null));
    }
}
