package pl.altkom.asc.lab.micronaut.poc.policy.readmodel;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.Policy;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class PolicyViewAssembler {

    static PolicyView map(Policy policy) {
        return new PolicyView(policy.getNumber());
    }
}
