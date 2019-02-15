package pl.altkom.asc.lab.micronaut.poc.policy.search.infrastructure.adapters.mock;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.reactivex.Maybe;
import pl.altkom.asc.lab.micronaut.poc.policy.search.readmodel.PolicyView;
import pl.altkom.asc.lab.micronaut.poc.policy.search.readmodel.PolicyViewRepository;

import javax.inject.Singleton;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import pl.altkom.asc.lab.micronaut.poc.policy.search.infrastructure.adapters.db.ElasticPolicyViewRepository;
import pl.altkom.asc.lab.micronaut.poc.policy.search.service.api.v1.queries.findpolicy.FindPolicyQuery;

@Replaces(ElasticPolicyViewRepository.class)
@Requires(env = Environment.TEST)
@Singleton
public class MockPolicyViewRepository implements PolicyViewRepository {

    private Map<String, PolicyView> policyMap = init();

    private Map<String, PolicyView> init() {
        Map<String, PolicyView> map = new LinkedHashMap<>();

        map.put("1234", new PolicyView(
                "1234",
                LocalDate.of(2019, 1, 1),
                LocalDate.of(2020,1,1),
                "Xxxx Yyyy")
        );
        map.put("1235", new PolicyView(
                "1235",
                LocalDate.of(2019, 1, 1),
                LocalDate.of(2020,1,1),
                "Xxxx Yyyy")
        );
        map.put("1236", new PolicyView("1236",
                LocalDate.of(2019, 1, 1),
                LocalDate.of(2020,1,1),
                "Xxxx Yyyy")
        );
        map.put("1237", new PolicyView("1237",
                LocalDate.of(2019, 1, 1),
                LocalDate.of(2020,1,1),
                "Xxxx Yyyy")
        );

        return map;
    }

    @Override
    public Maybe<List<PolicyView>> findAll(FindPolicyQuery query) {
        return Maybe.just(new ArrayList<>(policyMap.values()));
    }

    @Override
    public void save(PolicyView policy) {
        policyMap.put(policy.getNumber(), policy);
    }
}
