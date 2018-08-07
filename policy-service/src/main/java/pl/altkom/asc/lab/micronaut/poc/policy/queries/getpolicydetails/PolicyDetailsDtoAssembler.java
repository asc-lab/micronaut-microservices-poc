package pl.altkom.asc.lab.micronaut.poc.policy.queries.getpolicydetails;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.Cover;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.Policy;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.PolicyVersion;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.getpolicydetails.dto.PolicyDetailsDto;

import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class PolicyDetailsDtoAssembler {

    static PolicyDetailsDto map(Policy policy) {
        PolicyVersion policyVersion = policy.versions().lastVersion();

        return new PolicyDetailsDto(
                policy.getNumber(),
                policyVersion.getVersionValidityPeriod().getFrom(),
                policyVersion.getVersionValidityPeriod().getTo(),
                policyVersion.getPolicyHolder().getFullName(),
                policyVersion.getTotalPremiumAmount(),
                policyVersion.getProductCode(),
                policyVersion.getAccountNumber(),
                policyVersion.getCovers().stream()
                        .map(Cover::toString)
                        .sorted()
                        .collect(Collectors.toSet())
        );
    }
}
