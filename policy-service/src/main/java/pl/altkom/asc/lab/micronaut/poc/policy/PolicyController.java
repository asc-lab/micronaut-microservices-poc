package pl.altkom.asc.lab.micronaut.poc.policy;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.bus.CommandBus;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.PolicyOperations;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyclose.ClosePolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyclose.ClosePolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyregister.RegisterPolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyregister.RegisterPolicyResult;
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
    public RegisterPolicyResult register(RegisterPolicyCommand cmd) {
        return bus.executeCommand(cmd);
    }

    @Override
    public ClosePolicyResult close(ClosePolicyCommand cmd) {
        return bus.executeCommand(cmd);
    }
}
