package pl.altkom.asc.lab.micronaut.poc.policy;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.Client;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyclose.ClosePolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyclose.ClosePolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyregister.RegisterPolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyregister.RegisterPolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.policyfind.FindPolicyQueryResult;

@Client(id = "/policy-service", path = "/policies")
public interface PolicyTestClient {

    @Get("/")
    FindPolicyQueryResult policies();

    @Post("/")
    RegisterPolicyResult register(@Body RegisterPolicyCommand cmd);

    @Post("/close")
    ClosePolicyResult close(@Body ClosePolicyCommand cmd);
}
