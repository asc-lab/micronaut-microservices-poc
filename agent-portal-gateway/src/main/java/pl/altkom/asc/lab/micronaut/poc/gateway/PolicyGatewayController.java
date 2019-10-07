package pl.altkom.asc.lab.micronaut.poc.gateway;


import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.reactivex.Maybe;
import pl.altkom.asc.lab.micronaut.poc.gateway.client.v1.PolicyGatewayClient;
import pl.altkom.asc.lab.micronaut.poc.gateway.client.v1.PolicySearchGatewayClient;
import pl.altkom.asc.lab.micronaut.poc.policy.search.service.api.v1.queries.findpolicy.FindPolicyQueryResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createpolicy.CreatePolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createpolicy.CreatePolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.terminatepolicy.TerminatePolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.terminatepolicy.TerminatePolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.getpolicydetails.GetPolicyDetailsQueryResult;

import javax.inject.Inject;
import java.security.Principal;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/api/policies")
public class PolicyGatewayController {

    @Inject
    private PolicyGatewayClient policyClient;
    @Inject
    private PolicySearchGatewayClient policySearchClient;

    @Get
    Maybe<FindPolicyQueryResult> policies(@QueryValue(value = "q", defaultValue = "*") String q) {
        return policySearchClient.policies(q);
    }

    @Get("/{policyNumber}")
    GetPolicyDetailsQueryResult get(String policyNumber) {
        return policyClient.get(policyNumber);
    }

    @Post("/create")
    CreatePolicyResult create(CreatePolicyCommand cmd, Principal principal) {
        cmd.setAgentLogin(principal.getName());
        return policyClient.create(cmd);
    }

    @Post("/terminate")
    TerminatePolicyResult terminate(TerminatePolicyCommand cmd) {
        return policyClient.terminate(cmd);
    }

}
