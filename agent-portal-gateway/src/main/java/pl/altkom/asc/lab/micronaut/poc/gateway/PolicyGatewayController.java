package pl.altkom.asc.lab.micronaut.poc.gateway;


import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import pl.altkom.asc.lab.micronaut.poc.gateway.client.v1.PolicyGatewayClient;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createpolicy.CreatePolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createpolicy.CreatePolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.terminatepolicy.TerminatePolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.terminatepolicy.TerminatePolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.findpolicy.FindPolicyQueryResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.getpolicydetails.GetPolicyDetailsQueryResult;

import javax.inject.Inject;

@Controller("/api/policies")
public class PolicyGatewayController {

    @Inject
    private PolicyGatewayClient policyClient;

    @Get("/")
    FindPolicyQueryResult policies() {
        return policyClient.policies();
    }

    @Get("/{policyNumber}")
    GetPolicyDetailsQueryResult get(String policyNumber) {
        return policyClient.get(policyNumber);
    }

    @Post("/create")
    CreatePolicyResult create(CreatePolicyCommand cmd) {
        return policyClient.create(cmd);
    }

    @Post("/terminate")
    TerminatePolicyResult terminate(TerminatePolicyCommand cmd) {
        return policyClient.terminate(cmd);
    }

}
