package pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.adapters.db;

import io.micronaut.spring.tx.annotation.Transactional;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.Offer;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.OfferRepository;
import pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.annotations.RequiresJdbc;


@RequiresJdbc
@Singleton
@RequiredArgsConstructor
public class HibernateOffersRepository implements OfferRepository {

    private final SessionFactory sessionFactory;
    
    @Transactional
    @Override
    public Long add(Offer offer) {
        return (Long)currentSession().save(offer);
    }
    
    @Transactional
    @Override
    public Offer getByNumber(String number) {
        return query("from Offer o where o.number = :number")
                .setParameter("number", number)
                .uniqueResult();
    }
    
    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
    
    private Query<Offer> query(String queryText) {
        return currentSession().createQuery(queryText, Offer.class);
    }
}
