package pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1;

import lombok.*;

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
