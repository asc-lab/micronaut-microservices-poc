package pl.altkom.asc.lab.micronaut.poc.policy.commands.policyregister;

import lombok.*;
import pl.altkom.asc.lab.micronaut.poc.policy.commands.policyregister.dto.PolicyVersionDto;
import pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.bus.Command;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterPolicyCommand implements Command<RegisterPolicyResult> {
    @NotNull
    private PolicyVersionDto policyVersion;
}
