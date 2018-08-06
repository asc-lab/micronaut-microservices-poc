package pl.altkom.asc.lab.micronaut.poc.policy.domain;

public interface OfferRepository {
    Long add(Offer offer);
    
    Offer getByNumber(String number);
}
