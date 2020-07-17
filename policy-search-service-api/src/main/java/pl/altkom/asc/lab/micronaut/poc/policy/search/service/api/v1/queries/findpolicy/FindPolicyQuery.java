package pl.altkom.asc.lab.micronaut.poc.policy.search.service.api.v1.queries.findpolicy;

import pl.altkom.asc.lab.micronaut.poc.command.bus.api.Query;

import io.micronaut.core.annotation.Introspected;
import io.reactivex.Maybe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Introspected
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindPolicyQuery implements Query<Maybe<FindPolicyQueryResult>> {
    private String queryText; 
}
