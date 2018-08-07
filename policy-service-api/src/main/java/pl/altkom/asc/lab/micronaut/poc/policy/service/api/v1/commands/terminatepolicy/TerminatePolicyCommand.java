package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.terminatepolicy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.Command;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TerminatePolicyCommand implements Command<TerminatePolicyResult> {
    private String policyNumber;
}
