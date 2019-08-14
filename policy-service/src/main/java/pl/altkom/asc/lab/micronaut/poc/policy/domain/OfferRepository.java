package pl.altkom.asc.lab.micronaut.poc.policy.domain;

import io.micronaut.data.repository.GenericRepository;
import io.micronaut.data.annotation.*;
import io.micronaut.data.model.*;

@Repository
public interface OfferRepository extends GenericRepository<Offer,Long> {
    Offer getByNumber(String number);

    Offer save(Offer offer);
}
