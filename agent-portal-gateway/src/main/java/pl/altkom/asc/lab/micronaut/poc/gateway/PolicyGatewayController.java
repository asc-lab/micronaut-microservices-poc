package pl.altkom.asc.lab.micronaut.poc.gateway;


import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import pl.altkom.asc.lab.micronaut.poc.gateway.client.v1.PolicyGatewayClient;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyclose.ClosePolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyclose.ClosePolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyregister.RegisterPolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyregister.RegisterPolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.policyfind.FindPolicyQueryResult;

import javax.inject.Inject;

@Controller("/api/policies")
public class PolicyGatewayController {

    @Inject
    private PolicyGatewayClient policyClient;

    @Get("/")
    FindPolicyQueryResult policies() {
        return policyClient.policies();
    }

    @Post("/register")
    RegisterPolicyResult register(RegisterPolicyCommand cmd) {
        return policyClient.register(cmd);
    }

    @Post("/close")
    ClosePolicyResult close(ClosePolicyCommand cmd) {
        return policyClient.close(cmd);
    }

}
