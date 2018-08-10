package pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.adapters.db;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.Offer;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.OfferRepository;
import pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.annotations.RequiresJdbc;


@RequiresJdbc
@Singleton
public class HibernateOffersRepository implements OfferRepository {

    @Inject
    @CurrentSession
    private EntityManager entityManager;
    
    @Transactional
    @Override
    public void add(Offer offer) {
        entityManager.persist(offer);
    }
    
    @Transactional
    @Override
    public Offer getByNumber(String number) {
        return query("from Offer o where o.number = :number")
                .setParameter("number", number)
                .getSingleResult();
    }
    
    private TypedQuery<Offer> query(String queryText) {
        return entityManager.createQuery(queryText, Offer.class);
    }
}
