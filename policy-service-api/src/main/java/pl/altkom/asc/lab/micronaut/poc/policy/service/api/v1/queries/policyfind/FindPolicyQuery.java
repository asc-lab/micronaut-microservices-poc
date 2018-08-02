package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.policyfind;

import lombok.*;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.Query;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindPolicyQuery implements Query<FindPolicyQueryResult> {
    private String number;
}
