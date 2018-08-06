package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createpolicy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.Command;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyregister.dto.PersonDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePolicyCommand implements Command<CreatePolicyResult> {
    private String offerNumber;
    private PersonDto policyHolder;
}
