package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.findpolicy;

import lombok.*;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.Query;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindPolicyQuery implements Query<FindPolicyQueryResult> {
    private String number;
    //TODO add more search criteria
}
