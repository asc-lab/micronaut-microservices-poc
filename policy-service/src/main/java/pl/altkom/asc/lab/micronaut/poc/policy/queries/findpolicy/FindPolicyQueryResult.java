package pl.altkom.asc.lab.micronaut.poc.policy.queries.findpolicy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.altkom.asc.lab.micronaut.poc.policy.queries.findpolicy.dto.PolicyViewDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FindPolicyQueryResult {
    private List<PolicyViewDto> policies;
}
