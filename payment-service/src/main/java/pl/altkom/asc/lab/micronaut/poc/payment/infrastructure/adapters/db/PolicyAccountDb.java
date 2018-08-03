package pl.altkom.asc.lab.micronaut.poc.payment.infrastructure.adapters.db;

import io.micronaut.spring.tx.annotation.Transactional;
import java.util.Collection;
import java.util.Optional;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import pl.altkom.asc.lab.micronaut.poc.payment.domain.PolicyAccount;
import pl.altkom.asc.lab.micronaut.poc.payment.domain.PolicyAccountRepository;

@Singleton
@RequiredArgsConstructor
public class PolicyAccountDb implements PolicyAccountRepository {
    private final SessionFactory sessionFactory;
    
    @Transactional
    @Override
    public Optional<PolicyAccount> findForPolicy(String policyNumber) {
        return query("from PolicyAccount p left join fetch p.entries where p.policyNumber = :policyNumber")
                .setParameter("policyNumber", policyNumber)
                .uniqueResultOptional();
    }

    @Transactional
    @Override
    public Optional<PolicyAccount> findByNumber(String accountNumber) {
        return query("from PolicyAccount p left join fetch p.entries where p.policyAccountNumber = :accountNumber")
                .setParameter("accountNumber", accountNumber)
                .uniqueResultOptional();
    }
    
    @Transactional
    @Override
    public void add(PolicyAccount policyAccount) {
        currentSession().save(policyAccount);
    }

    @Transactional
    @Override
    public Collection<PolicyAccount> findAll() {
        return query("from PolicyAccount p").list();
    }
    
    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
    
    private Query<PolicyAccount> query(String text) {
        return currentSession().createQuery(text, PolicyAccount.class);
    }
}
