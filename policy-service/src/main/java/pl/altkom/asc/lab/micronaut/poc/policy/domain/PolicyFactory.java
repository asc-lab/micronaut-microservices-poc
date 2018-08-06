package pl.altkom.asc.lab.micronaut.poc.policy.domain;

import java.time.LocalDate;
import java.util.UUID;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.vo.DateRange;

public class PolicyFactory {
    
     public Policy fromOffer(Offer offer, Person policyHolder) {
         Policy policy = new Policy(UUID.randomUUID().toString());
         addVersion(offer, policyHolder, policy);
         return policy;
     }

    public void addVersion(Offer offer, Person policyHolder, Policy policy) {
        PolicyVersion version = policy.versions().add(
                1L,
                offer.getProductCode(),
                policyHolder,
                UUID.randomUUID().toString(),
                DateRange.between(offer.getPolicyFrom(), offer.getPolicyTo()),
                DateRange.between(offer.getPolicyFrom(), LocalDate.MAX),
                offer.getTotalPrice()
        );

        offer.getCoversPrices().entrySet().forEach(coverWithPrice -> version.covers().add(coverWithPrice.getKey(), coverWithPrice.getValue()));
    }
}
