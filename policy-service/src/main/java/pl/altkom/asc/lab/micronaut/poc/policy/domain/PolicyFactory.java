package pl.altkom.asc.lab.micronaut.poc.policy.domain;

import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyregister.dto.*;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.vo.DateRange;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.vo.MonetaryAmount;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.vo.Percent;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.vo.Quantity;

public class PolicyFactory {

    public Policy create(PolicyVersionDto policyVersion) {
        Policy policy = new Policy(policyVersion.getPolicyNumber());
        addVersion(policyVersion, policy);
        return policy;
    }

    public void addVersion(PolicyVersionDto policyVersion, Policy policy) {
        PolicyVersion version = policy.versions().add(
                policyVersion.getVersionNumber(),
                policyVersion.getProductCode(),
                createPolicyHolder(policyVersion.getPolicyHolder()),
                policyVersion.getAccountNumber(),
                DateRange.between(policyVersion.getPolicyValidFrom(), policyVersion.getPolicyValidTo()),
                DateRange.between(policyVersion.getVersionValidFrom(), policyVersion.getVersionValidTo())
        );

        policyVersion.getCovers().forEach(c -> addCover(c, version));
    }

    private void addCover(CoverDto cover, PolicyVersion version) {
        Cover newCover = version.covers().add(cover.getCode());
        cover.getServices().forEach(s -> addService(s, newCover));
    }

    private void addService(ServiceDto service, Cover newCover) {
        newCover.services().add(
            service.getCode(),
            addCoPayment(service.getCoPayment()),
            addLimit(service.getLimit())
        );
    }

    private Limit addLimit(LimitDto limit) {
        if (limit == null)
            return null;

        return new Limit(
                LimitPeriod.valueOf(limit.getPeriodTypeCode()),
                limit.getMaxAmount() != null ? MonetaryAmount.from(limit.getMaxAmount()) : null,
                limit.getMaxQuantity() != null ? Quantity.of(limit.getMaxQuantity()) : null
        );
    }

    private CoPayment addCoPayment(CoPaymentDto coPayment) {
        if (coPayment == null)
            return CoPayment.empty();

        return coPayment.getPercent() != null ?
                CoPayment.percent(Percent.of(coPayment.getPercent()))
                : CoPayment.amount(MonetaryAmount.from(coPayment.getAmount()));
    }

    private Person createPolicyHolder(PersonDto person) {
        return new Person(person.getFirstName(), person.getLastName(), person.getPesel());
    }
}
