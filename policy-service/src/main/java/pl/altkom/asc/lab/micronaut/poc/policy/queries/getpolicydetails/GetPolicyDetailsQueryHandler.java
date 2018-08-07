package pl.altkom.asc.lab.micronaut.poc.policy.queries.getpolicydetails;

import io.micronaut.spring.tx.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.Policy;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.PolicyRepository;
import pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.bus.QueryHandler;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.getpolicydetails.GetPolicyDetailsQuery;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.getpolicydetails.GetPolicyDetailsQueryResult;
import pl.altkom.asc.lab.micronaut.poc.policy.shared.exceptions.BusinessException;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
@RequiredArgsConstructor
public class GetPolicyDetailsQueryHandler implements QueryHandler<GetPolicyDetailsQueryResult, GetPolicyDetailsQuery> {

    private final PolicyRepository policyRepository;

    @Transactional(readOnly = true)
    @Override
    public GetPolicyDetailsQueryResult handle(GetPolicyDetailsQuery query) {
        Optional<Policy> policyOpt = policyRepository.findByNumber(query.getNumber());
        if (!policyOpt.isPresent())
            throw new BusinessException("POLICY NOT FOUND");

        return new GetPolicyDetailsQueryResult(PolicyDetailsDtoAssembler.map(policyOpt.get()));
    }
}
