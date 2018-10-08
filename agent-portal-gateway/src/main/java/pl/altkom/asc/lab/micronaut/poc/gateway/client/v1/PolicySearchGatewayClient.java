package pl.altkom.asc.lab.micronaut.poc.gateway.client.v1;

import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Retryable;
import pl.altkom.asc.lab.micronaut.poc.policy.search.service.api.v1.PolicySearchOperations;

@Client(id = "policy-search-service", path = "/policies")
@Retryable(attempts = "2", delay = "2s")
public interface PolicySearchGatewayClient extends PolicySearchOperations {
}
