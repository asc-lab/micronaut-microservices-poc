package pl.altkom.asc.lab.micronaut.poc;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.Client;
import pl.altkom.asc.lab.micronaut.poc.policy.search.service.api.v1.queries.findpolicy.FindPolicyQueryResult;

@Client(id = "/policy-search-service", path = "/policies")
public interface PolicySearchTestClient {

    @Get
    FindPolicyQueryResult policies();
}
