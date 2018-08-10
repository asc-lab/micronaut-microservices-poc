package pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.adapters.db;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;
import pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.annotations.RequiresJdbc;
import pl.altkom.asc.lab.micronaut.poc.policy.readmodel.PolicyView;
import pl.altkom.asc.lab.micronaut.poc.policy.readmodel.PolicyViewRepository;

import javax.inject.Singleton;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@RequiresJdbc
@Singleton
public class HibernatePolicyViewRepository implements PolicyViewRepository {
    @Inject
    @CurrentSession
    private EntityManager entityManager;

    @Transactional
    @Override
    public List<PolicyView> findAll() {
        return entityManager
                .createQuery("from PolicyView pv", PolicyView.class)
                .getResultList();
    }

    @Transactional
    @Override
    public void save(PolicyView view) {
        entityManager.persist(view);
    }
}
