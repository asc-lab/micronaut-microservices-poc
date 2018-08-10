package pl.altkom.asc.lab.micronaut.poc.policy.domain;

public interface OfferRepository {
    void add(Offer offer);
    
    Offer getByNumber(String number);
}
