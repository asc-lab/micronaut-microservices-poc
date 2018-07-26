package pl.altkom.asc.lab.micronaut.poc.policy.client;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.Client;
import pl.altkom.asc.lab.micronaut.poc.policy.commands.policyclose.ClosePolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.commands.policyclose.ClosePolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.commands.policyregister.RegisterPolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.commands.policyregister.RegisterPolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.queries.findpolicy.FindPolicyQueryResult;

@Client(id = "/policy-service", path = "/policies")
public interface PolicyClient {

    @Get("/")
    FindPolicyQueryResult policies();

    @Post("/")
    RegisterPolicyResult register(@Body RegisterPolicyCommand cmd);

    @Post("/close")
    ClosePolicyResult close(@Body ClosePolicyCommand cmd);
}
