package pl.altkom.asc.lab.micronaut.poc.policy.readmodel;

import pl.altkom.asc.lab.micronaut.poc.policy.domain.Policy;

import javax.inject.Inject;

abstract class AbstractPolicyListener {

    @Inject
    private PolicyViewRepository policyViewRepository;

    void saveMappedPolicy(Policy policy) {
        PolicyView view = PolicyViewAssembler.map(policy);
        policyViewRepository.save(view);
    }
}
