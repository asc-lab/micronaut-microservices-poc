package pl.altkom.asc.lab.micronaut.poc.policy.domain;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.GenericRepository;
import java.util.List;

@Repository
public interface OfferRepository extends GenericRepository<Offer, Long> {
    Offer getByNumber(String number);

    Offer save(Offer offer);
    
    List<Offer> findAll();
}


