package pl.altkom.asc.lab.micronaut.poc.gateway.client.v1;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.Client;
import io.micronaut.retry.annotation.Retryable;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.PolicyOperations;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createoffer.CreateOfferCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createoffer.CreateOfferResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createpolicy.CreatePolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createpolicy.CreatePolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.terminatepolicy.TerminatePolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.terminatepolicy.TerminatePolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.getpolicydetails.GetPolicyDetailsQueryResult;

@Client(id = "policy-service")
@Retryable(attempts = "2", delay = "2s")
public interface PolicyGatewayClient extends PolicyOperations {

    @Post("/offers")
    CreateOfferResult createOffer(CreateOfferCommand cmd);

    @Override
    @Get("/policies/{policyNumber}")
    GetPolicyDetailsQueryResult get(String policyNumber);

    @Override
    @Post("/policies")
    CreatePolicyResult create(CreatePolicyCommand cmd);

    @Override
    @Post("/policies/terminate")
    TerminatePolicyResult terminate(TerminatePolicyCommand cmd);
}
