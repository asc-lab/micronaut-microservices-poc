package pl.altkom.asc.lab.micronaut.poc.policy.domain;

import pl.altkom.asc.lab.micronaut.poc.policy.domain.vo.DateRange;

import java.util.UUID;

public class PolicyFactory {

    public Policy fromOffer(Offer offer, Person policyHolder, AgentRef agent) {
        Policy policy = new Policy(UUID.randomUUID().toString(), agent);
        addVersion(offer, policyHolder, policy);
        return policy;
    }

    private void addVersion(Offer offer, Person policyHolder, Policy policy) {
        PolicyVersion version = policy.versions().add(
                1L,
                offer.getProductCode(),
                policyHolder,
                UUID.randomUUID().toString(),
                DateRange.between(offer.getPolicyFrom(), offer.getPolicyTo()),
                DateRange.between(offer.getPolicyFrom(), offer.getPolicyTo()),
                offer.getTotalPrice()
        );

        offer.getCoversPrices().forEach((key, value) -> version.covers().add(key, value));
    }
}
