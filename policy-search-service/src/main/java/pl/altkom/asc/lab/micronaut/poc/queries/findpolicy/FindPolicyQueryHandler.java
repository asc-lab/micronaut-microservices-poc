package pl.altkom.asc.lab.micronaut.poc.queries.findpolicy;

import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.command.bus.QueryHandler;
import pl.altkom.asc.lab.micronaut.poc.policy.search.service.api.v1.queries.findpolicy.FindPolicyQuery;
import pl.altkom.asc.lab.micronaut.poc.policy.search.service.api.v1.queries.findpolicy.FindPolicyQueryResult;
import pl.altkom.asc.lab.micronaut.poc.readmodel.PolicyView;
import pl.altkom.asc.lab.micronaut.poc.readmodel.PolicyViewRepository;

import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
@RequiredArgsConstructor
public class FindPolicyQueryHandler implements QueryHandler<FindPolicyQueryResult, FindPolicyQuery> {

    private final PolicyViewRepository policyViewRepository;

    @Override
    public FindPolicyQueryResult handle(FindPolicyQuery query) {
        List<PolicyView> policies = policyViewRepository.findAll();

        return new FindPolicyQueryResult(
                policies.stream()
                        .map(PolicyListItemDtoAssembler::map)
                        .collect(Collectors.toList())
        );
    }


}
