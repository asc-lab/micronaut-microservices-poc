package pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.adapters.mock;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.inject.Singleton;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.Offer;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.OfferRepository;
import pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.adapters.db.HibernateOffersRepository;

@Replaces(HibernateOffersRepository.class)
@Requires(env = Environment.TEST)
@Singleton
public class MockOfferRepository implements OfferRepository{
    private Map<String, Offer> map = new ConcurrentHashMap<>();
    
    @Override
    public void add(Offer offer) {
        map.put(offer.getNumber(), offer);
    }

    @Override
    public Offer getByNumber(String number) {
        return map.get(number);
    }
    
}
