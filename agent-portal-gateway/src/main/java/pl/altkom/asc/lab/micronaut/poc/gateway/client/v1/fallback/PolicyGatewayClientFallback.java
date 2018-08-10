package pl.altkom.asc.lab.micronaut.poc.gateway.client.v1.fallback;

import io.micronaut.retry.annotation.Fallback;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.PolicyOperations;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createpolicy.CreatePolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createpolicy.CreatePolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.terminatepolicy.TerminatePolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.terminatepolicy.TerminatePolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.getpolicydetails.GetPolicyDetailsQueryResult;

import javax.inject.Singleton;
import javax.validation.constraints.NotNull;

@Singleton
@Fallback
public class PolicyGatewayClientFallback implements PolicyOperations {

    @Override
    public GetPolicyDetailsQueryResult get(@NotNull String policyNumber) {
        return GetPolicyDetailsQueryResult.empty();
    }

    @Override
    public CreatePolicyResult create(@NotNull CreatePolicyCommand cmd) {
        return new CreatePolicyResult(null);
    }

    @Override
    public TerminatePolicyResult terminate(@NotNull TerminatePolicyCommand cmd) {
        return TerminatePolicyResult.empty();
    }
}
