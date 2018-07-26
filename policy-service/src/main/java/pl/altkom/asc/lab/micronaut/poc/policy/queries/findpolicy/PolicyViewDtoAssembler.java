package pl.altkom.asc.lab.micronaut.poc.policy.queries.findpolicy;

import pl.altkom.asc.lab.micronaut.poc.policy.queries.findpolicy.dto.PolicyViewDto;
import pl.altkom.asc.lab.micronaut.poc.policy.readmodel.PolicyView;

final class PolicyViewDtoAssembler {

    static PolicyViewDto map(PolicyView policy) {
        return new PolicyViewDto(
                policy.getNumber()
        );
    }
}
