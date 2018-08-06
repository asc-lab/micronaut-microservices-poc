package pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.adapters.web;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.validation.Validated;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.bus.CommandBus;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.PolicyOperations;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createpolicy.CreatePolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createpolicy.CreatePolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyclose.TerminatePolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyclose.TerminatePolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.policyfind.FindPolicyQuery;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.policyfind.FindPolicyQueryResult;

@RequiredArgsConstructor
@Validated
@Controller("/policies")
public class PolicyController implements PolicyOperations {

    private final CommandBus bus;

    @Override
    public FindPolicyQueryResult policies() {
        return bus.executeQuery(new FindPolicyQuery());
    }

    @Override
    public CreatePolicyResult create(@Body @NotNull CreatePolicyCommand cmd) {
        return bus.executeCommand(cmd);
    }

    @Override
    public TerminatePolicyResult terminate(TerminatePolicyCommand cmd) {
        return bus.executeCommand(cmd);
    }
}
