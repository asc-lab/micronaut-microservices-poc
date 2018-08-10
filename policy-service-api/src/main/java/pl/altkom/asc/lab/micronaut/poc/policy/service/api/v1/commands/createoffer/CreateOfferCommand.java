package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createoffer;

import lombok.*;
import pl.altkom.asc.lab.micronaut.poc.command.bus.api.Command;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createoffer.dto.QuestionAnswer;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOfferCommand implements Command<CreateOfferResult> {
    private String productCode;
    private LocalDate policyFrom;
    private LocalDate policyTo;
    private List<String> selectedCovers;
    private List<QuestionAnswer> answers;
}
