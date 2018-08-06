package pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.adapters.mock;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.Policy;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.PolicyRepository;
import pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.adapters.db.HibernatePolicyViewRepository;

import javax.inject.Singleton;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Replaces(HibernatePolicyViewRepository.class)
@Requires(env = Environment.TEST)
@Singleton
public class MockPolicyRepository implements PolicyRepository {

    private Map<String, Policy> policyMap = init();

    private Map<String, Policy> init() {
        Map<String, Policy> map = new LinkedHashMap<>();

        map.put("1234", new Policy(1L, "1234", new HashSet<>()));
        map.put("1235", new Policy(2L, "1235", new HashSet<>()));
        map.put("1236", new Policy(3L, "1236", new HashSet<>()));

        return map;
    }

    @Override
    public Optional<Policy> findByNumber(String number) {
        return Optional.ofNullable(policyMap.get(number));
    }

    @Override
    public void add(Policy policy) {
        policyMap.put(policy.getNumber(), policy);
    }
}
