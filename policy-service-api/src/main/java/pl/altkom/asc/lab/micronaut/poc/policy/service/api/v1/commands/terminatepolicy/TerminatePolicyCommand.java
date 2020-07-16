package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.terminatepolicy;

import pl.altkom.asc.lab.micronaut.poc.command.bus.api.Command;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Introspected
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TerminatePolicyCommand implements Command<TerminatePolicyResult> {
    private String policyNumber;
}
