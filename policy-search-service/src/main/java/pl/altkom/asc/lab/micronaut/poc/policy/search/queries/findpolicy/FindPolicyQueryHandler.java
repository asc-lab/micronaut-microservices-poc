package pl.altkom.asc.lab.micronaut.poc.policy.search.queries.findpolicy;

import io.reactivex.Maybe;
import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.command.bus.QueryHandler;
import pl.altkom.asc.lab.micronaut.poc.policy.search.service.api.v1.queries.findpolicy.FindPolicyQuery;
import pl.altkom.asc.lab.micronaut.poc.policy.search.service.api.v1.queries.findpolicy.FindPolicyQueryResult;
import pl.altkom.asc.lab.micronaut.poc.policy.search.readmodel.PolicyViewRepository;

import javax.inject.Singleton;

@Singleton
@RequiredArgsConstructor
public class FindPolicyQueryHandler implements QueryHandler<Maybe<FindPolicyQueryResult>, FindPolicyQuery> {

    private final PolicyViewRepository policyViewRepository;

    @Override
    public Maybe<FindPolicyQueryResult> handle(FindPolicyQuery query) {
        return policyViewRepository
                .findAll(query)
                .map(PolicyQueryResultAssembler::constructResult);
    }

}
