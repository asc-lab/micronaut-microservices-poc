package pl.altkom.asc.lab.micronaut.poc.policy;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.Client;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createpolicy.CreatePolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createpolicy.CreatePolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyclose.TerminatePolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyclose.TerminatePolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.policyfind.FindPolicyQueryResult;

@Client(id = "/policy-service", path = "/policies")
public interface PolicyTestClient {

    @Get("/")
    FindPolicyQueryResult policies();

    @Post("/")
    CreatePolicyResult create(@Body CreatePolicyCommand  cmd);

    @Post("/terminate")
    TerminatePolicyResult terminate(@Body TerminatePolicyCommand cmd);
}
