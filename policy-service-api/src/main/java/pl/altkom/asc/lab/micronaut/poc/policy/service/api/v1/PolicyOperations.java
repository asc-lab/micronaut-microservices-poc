package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyclose.TerminatePolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyclose.TerminatePolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.policyfind.FindPolicyQueryResult;

import javax.validation.constraints.NotNull;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createpolicy.CreatePolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createpolicy.CreatePolicyResult;

public interface PolicyOperations {

    @Get("/")
    FindPolicyQueryResult policies();

    @Post("/")
    CreatePolicyResult create(@Body @NotNull CreatePolicyCommand cmd);

    @Post("/terminate")
    TerminatePolicyResult terminate(@Body @NotNull TerminatePolicyCommand cmd);
}
