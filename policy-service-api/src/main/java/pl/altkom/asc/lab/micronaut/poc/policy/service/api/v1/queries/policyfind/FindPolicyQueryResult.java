package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.policyfind;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.policyfind.dto.PolicyViewDto;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FindPolicyQueryResult {
    private List<PolicyViewDto> policies;

    public static FindPolicyQueryResult empty() {
        return new FindPolicyQueryResult(Collections.emptyList());
    }
}
