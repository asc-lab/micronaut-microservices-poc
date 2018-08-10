package pl.altkom.asc.lab.micronaut.poc.infrastructure.adapters.db;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.altkom.asc.lab.micronaut.poc.infrastructure.annotations.RequiresJdbc;
import pl.altkom.asc.lab.micronaut.poc.readmodel.PolicyView;
import pl.altkom.asc.lab.micronaut.poc.readmodel.PolicyViewRepository;

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
