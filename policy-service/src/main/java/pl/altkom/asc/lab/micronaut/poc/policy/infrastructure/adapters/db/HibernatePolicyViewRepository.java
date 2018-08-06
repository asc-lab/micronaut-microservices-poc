package pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.adapters.db;

import io.micronaut.spring.tx.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.annotations.RequiresJdbc;
import pl.altkom.asc.lab.micronaut.poc.policy.readmodel.PolicyView;
import pl.altkom.asc.lab.micronaut.poc.policy.readmodel.PolicyViewRepository;

import javax.inject.Singleton;
import java.util.List;

@RequiresJdbc
@Singleton
@RequiredArgsConstructor
public class HibernatePolicyViewRepository implements PolicyViewRepository {

    private final SessionFactory sessionFactory;

    @Transactional
    @Override
    public List<PolicyView> findAll() {
        return currentSession()
                .createQuery("from PolicyView pv", PolicyView.class)
                .list();
    }

    @Transactional
    @Override
    public void save(PolicyView view) {
        currentSession().save(view);
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
}
