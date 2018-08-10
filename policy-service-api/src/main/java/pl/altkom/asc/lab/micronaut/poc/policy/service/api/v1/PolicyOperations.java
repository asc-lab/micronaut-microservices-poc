package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createpolicy.CreatePolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createpolicy.CreatePolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.terminatepolicy.TerminatePolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.terminatepolicy.TerminatePolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.getpolicydetails.GetPolicyDetailsQueryResult;

import javax.validation.constraints.NotNull;

public interface PolicyOperations {

    @Get("/{policyNumber}")
    GetPolicyDetailsQueryResult get(@NotNull String policyNumber);

    @Post("/")
    CreatePolicyResult create(@Body @NotNull CreatePolicyCommand cmd);

    @Post("/terminate")
    TerminatePolicyResult terminate(@Body @NotNull TerminatePolicyCommand cmd);
}
