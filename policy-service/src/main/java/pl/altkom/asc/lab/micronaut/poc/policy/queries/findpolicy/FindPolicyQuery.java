package pl.altkom.asc.lab.micronaut.poc.policy.queries.findpolicy;

import lombok.*;
import pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.bus.Query;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindPolicyQuery implements Query<FindPolicyQueryResult> {
    private String number;
}
