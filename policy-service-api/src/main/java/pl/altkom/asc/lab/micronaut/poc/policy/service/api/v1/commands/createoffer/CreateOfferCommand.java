package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createoffer;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.Command;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createoffer.dto.QuestionAnswer;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOfferCommand implements Command<CreateOfferResult> {
    private String productCode;
    private LocalDate policyFrom;
    private LocalDate policyTo;
    private List<String> selectedCovers;
    private List<QuestionAnswer> answers;
}
