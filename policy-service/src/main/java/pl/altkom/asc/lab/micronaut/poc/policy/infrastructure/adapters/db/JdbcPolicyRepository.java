package pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.adapters.db;

import io.micronaut.spring.tx.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.Policy;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.PolicyRepository;
import pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.annotations.RequiresJdbc;

import javax.inject.Singleton;
import java.util.Optional;

@RequiresJdbc
@Singleton
@RequiredArgsConstructor
public class JdbcPolicyRepository implements PolicyRepository {

    private final SessionFactory sessionFactory;

    @Transactional
    @Override
    public Optional<Policy> findByNumber(String number) {
        return currentSession()
                .createQuery("from Policy p where p.number = :number", Policy.class)
                .setParameter("number", number)
                .uniqueResultOptional();
    }

    @Transactional
    @Override
    public void save(Policy policy) {
        currentSession().save(policy);
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
}
