package pl.altkom.asc.lab.micronaut.poc.policy.commands.policyclose;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.bus.Command;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClosePolicyCommand implements Command<ClosePolicyResult> {
    private String policyNumber;
}
