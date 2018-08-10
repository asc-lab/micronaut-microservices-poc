package pl.altkom.asc.lab.micronaut.poc.policy.search.readmodel;

import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.dto.PolicyDto;

import javax.inject.Inject;

abstract class AbstractPolicyListener {

    @Inject
    private PolicyViewRepository policyViewRepository;

    void saveMappedPolicy(PolicyDto policy) {
        PolicyView view = PolicyViewAssembler.map(policy);
        policyViewRepository.save(view);
    }
}
