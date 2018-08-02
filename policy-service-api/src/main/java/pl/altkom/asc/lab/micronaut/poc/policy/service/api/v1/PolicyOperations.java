package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyclose.ClosePolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyclose.ClosePolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyregister.RegisterPolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyregister.RegisterPolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.policyfind.FindPolicyQueryResult;

import javax.validation.constraints.NotNull;

public interface PolicyOperations {

    @Get("/")
    FindPolicyQueryResult policies();

    @Post("/")
    RegisterPolicyResult register(@Body @NotNull RegisterPolicyCommand cmd);

    @Post("/close")
    ClosePolicyResult close(@Body @NotNull ClosePolicyCommand cmd);
}
