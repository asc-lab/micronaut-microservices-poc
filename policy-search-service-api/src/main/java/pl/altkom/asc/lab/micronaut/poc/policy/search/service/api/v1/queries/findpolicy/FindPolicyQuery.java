package pl.altkom.asc.lab.micronaut.poc.policy.search.service.api.v1.queries.findpolicy;

import lombok.*;
import pl.altkom.asc.lab.micronaut.poc.command.bus.api.Query;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindPolicyQuery implements Query<FindPolicyQueryResult> {
    private String number;
}
