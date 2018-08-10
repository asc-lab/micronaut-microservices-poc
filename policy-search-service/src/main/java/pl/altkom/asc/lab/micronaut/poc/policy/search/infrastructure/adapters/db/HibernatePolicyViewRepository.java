package pl.altkom.asc.lab.micronaut.poc.policy.search.infrastructure.adapters.db;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;
import pl.altkom.asc.lab.micronaut.poc.policy.search.infrastructure.annotations.RequiresJdbc;
import pl.altkom.asc.lab.micronaut.poc.policy.search.readmodel.PolicyView;
import pl.altkom.asc.lab.micronaut.poc.policy.search.readmodel.PolicyViewRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import java.util.List;

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
