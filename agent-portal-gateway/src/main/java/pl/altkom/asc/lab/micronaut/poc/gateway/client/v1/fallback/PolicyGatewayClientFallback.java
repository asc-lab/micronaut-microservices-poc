package pl.altkom.asc.lab.micronaut.poc.gateway.client.v1.fallback;

import io.micronaut.retry.annotation.Fallback;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.PolicyOperations;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyclose.ClosePolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyclose.ClosePolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyregister.RegisterPolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyregister.RegisterPolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.policyfind.FindPolicyQueryResult;

import javax.inject.Singleton;
import javax.validation.constraints.NotNull;

@Singleton
@Fallback
public class PolicyGatewayClientFallback implements PolicyOperations {

    @Override
    public FindPolicyQueryResult policies() {
        return FindPolicyQueryResult.empty();
    }

    @Override
    public RegisterPolicyResult register(@NotNull RegisterPolicyCommand cmd) {
        return RegisterPolicyResult.empty();
    }

    @Override
    public ClosePolicyResult close(@NotNull ClosePolicyCommand cmd) {
        return ClosePolicyResult.empty();
    }
}
