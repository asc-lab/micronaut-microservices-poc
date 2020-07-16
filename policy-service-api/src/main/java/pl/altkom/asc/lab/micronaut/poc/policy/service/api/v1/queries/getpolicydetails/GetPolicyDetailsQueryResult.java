package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.getpolicydetails;

import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.getpolicydetails.dto.PolicyDetailsDto;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Introspected
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetPolicyDetailsQueryResult {
    private PolicyDetailsDto policy;

    public static GetPolicyDetailsQueryResult empty() {
        return new GetPolicyDetailsQueryResult(new PolicyDetailsDto());
    }
}
