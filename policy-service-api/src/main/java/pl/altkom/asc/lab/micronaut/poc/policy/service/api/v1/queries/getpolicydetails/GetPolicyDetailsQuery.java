package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.getpolicydetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.command.bus.api.Query;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetPolicyDetailsQuery implements Query<GetPolicyDetailsQueryResult> {
    private String number;
}
