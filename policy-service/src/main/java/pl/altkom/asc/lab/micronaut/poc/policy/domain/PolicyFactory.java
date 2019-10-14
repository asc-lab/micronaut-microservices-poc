package pl.altkom.asc.lab.micronaut.poc.policy.domain;

import java.util.UUID;

public class PolicyFactory {

    public Policy fromOffer(Offer offer, Person policyHolder, AgentRef agent) {
        Policy policy = new Policy(UUID.randomUUID().toString(), agent);
        policy.addVersion(offer, policyHolder);
        return policy;
    }


}
