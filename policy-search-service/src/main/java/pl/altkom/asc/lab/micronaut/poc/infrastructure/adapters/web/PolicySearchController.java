package pl.altkom.asc.lab.micronaut.poc.infrastructure.adapters.web;

import io.micronaut.http.annotation.Controller;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.command.bus.CommandBus;
import pl.altkom.asc.lab.micronaut.poc.policy.search.service.api.v1.PolicySearchOperations;
import pl.altkom.asc.lab.micronaut.poc.policy.search.service.api.v1.queries.findpolicy.FindPolicyQuery;
import pl.altkom.asc.lab.micronaut.poc.policy.search.service.api.v1.queries.findpolicy.FindPolicyQueryResult;

@RequiredArgsConstructor
@Validated
@Controller("/policies")
public class PolicySearchController implements PolicySearchOperations {

    private final CommandBus bus;

    @Override
    public FindPolicyQueryResult policies() {
        return bus.executeQuery(new FindPolicyQuery());
    }
}
