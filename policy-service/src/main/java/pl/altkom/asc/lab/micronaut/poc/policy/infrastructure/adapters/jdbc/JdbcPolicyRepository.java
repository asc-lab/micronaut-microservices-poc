package pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.adapters.jdbc;

import pl.altkom.asc.lab.micronaut.poc.policy.domain.Policy;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.PolicyRepository;
import pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.annotations.RequiresJdbc;

import javax.inject.Singleton;
import java.util.Optional;

//@RequiresJdbc
//@Singleton
//public class JdbcPolicyRepository implements PolicyRepository {
//
//    @Override
//    public Optional<Policy> findByNumber(String number) {
//        return Optional.empty();
//    }
//
//    @Override
//    public void save(Policy newPolicy) {
//
//    }
//}
