package pl.altkom.asc.lab.micronaut.poc.policy.search.service.api.v1.queries.findpolicy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.policy.search.service.api.v1.queries.findpolicy.dto.PolicyListItemDto;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FindPolicyQueryResult {
    private List<PolicyListItemDto> policies;

    public static FindPolicyQueryResult empty() {
        return new FindPolicyQueryResult(Collections.emptyList());
    }
}
