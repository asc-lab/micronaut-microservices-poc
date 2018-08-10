package pl.altkom.asc.lab.micronaut.poc.payment.infrastructure.adapters.db;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;
import java.util.Collection;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import pl.altkom.asc.lab.micronaut.poc.payment.domain.PolicyAccount;
import pl.altkom.asc.lab.micronaut.poc.payment.domain.PolicyAccountRepository;

@Singleton
public class PolicyAccountDb implements PolicyAccountRepository {
    @Inject
    @CurrentSession
    private EntityManager entityManager;
    
    @Transactional
    @Override
    public Optional<PolicyAccount> findForPolicy(String policyNumber) {
        return query("from PolicyAccount p left join fetch p.entries where p.policyNumber = :policyNumber")
                .setParameter("policyNumber", policyNumber)
                .getResultStream()
                .findFirst();
    }

    @Transactional
    @Override
    public Optional<PolicyAccount> findByNumber(String accountNumber) {
        return query("from PolicyAccount p left join fetch p.entries where p.policyAccountNumber = :accountNumber")
                .setParameter("accountNumber", accountNumber)
                .getResultStream()
                .findFirst();
    }
    
    @Transactional
    @Override
    public void add(PolicyAccount policyAccount) {
        entityManager.persist(policyAccount);
    }

    @Transactional
    @Override
    public Collection<PolicyAccount> findAll() {
        return query("from PolicyAccount p").getResultList();
    }
    
    private TypedQuery<PolicyAccount> query(String text) {
        return entityManager.createQuery(text, PolicyAccount.class);
    }
}
