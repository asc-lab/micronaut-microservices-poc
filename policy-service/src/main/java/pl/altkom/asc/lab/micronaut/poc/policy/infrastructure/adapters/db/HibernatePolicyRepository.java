package pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.adapters.db;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.Policy;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.PolicyRepository;
import pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.annotations.RequiresJdbc;

import javax.inject.Singleton;
import java.util.Optional;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@RequiresJdbc
@Singleton
public class HibernatePolicyRepository implements PolicyRepository {
    @Inject
    @CurrentSession
    private EntityManager entityManager;

    @Transactional
    @Override
    public Optional<Policy> findByNumber(String number) {
        return entityManager
                .createQuery("from Policy p where p.number = :number", Policy.class)
                .setParameter("number", number)
                .getResultStream()
                .findFirst();
    }

    @Transactional
    @Override
    public void add(Policy policy) {
        entityManager.persist(policy);
    }
}
