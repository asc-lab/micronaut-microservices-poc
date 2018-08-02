package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyregister;

import lombok.*;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.Command;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyregister.dto.PolicyVersionDto;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterPolicyCommand implements Command<RegisterPolicyResult> {

    private PolicyVersionDto policyVersion;
}
