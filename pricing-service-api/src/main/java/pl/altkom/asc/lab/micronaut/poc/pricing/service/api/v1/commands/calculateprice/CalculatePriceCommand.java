package pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.commands.calculateprice;

import lombok.*;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.commands.calculateprice.dto.QuestionAnswer;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalculatePriceCommand {
    private String productCode;
    private LocalDate policyFrom;
    private LocalDate policyTo;
    private List<String> selectedCovers;
    private List<QuestionAnswer> answers;
}
