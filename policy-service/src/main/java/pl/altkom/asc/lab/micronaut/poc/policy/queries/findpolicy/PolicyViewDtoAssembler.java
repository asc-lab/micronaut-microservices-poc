package pl.altkom.asc.lab.micronaut.poc.policy.queries.findpolicy;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.policyfind.dto.PolicyViewDto;
import pl.altkom.asc.lab.micronaut.poc.policy.readmodel.PolicyView;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class PolicyViewDtoAssembler {

    static PolicyViewDto map(PolicyView policy) {
        return new PolicyViewDto(
                policy.getNumber(),
                policy.getDateFrom(),
                policy.getDateTo(),
                policy.getPolicyHolder(),
                policy.getTotalPremium()
        );
    }
}
