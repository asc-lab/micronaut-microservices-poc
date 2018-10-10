package pl.altkom.asc.lab.micronaut.poc.policy.search.readmodel;

import io.reactivex.Maybe;
import java.util.List;
import pl.altkom.asc.lab.micronaut.poc.policy.search.service.api.v1.queries.findpolicy.FindPolicyQuery;

public interface PolicyViewRepository {

    Maybe<List<PolicyView>> findAll(FindPolicyQuery query);

    void save(PolicyView view);
}
