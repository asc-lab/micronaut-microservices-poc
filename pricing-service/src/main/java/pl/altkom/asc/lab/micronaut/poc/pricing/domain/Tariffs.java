package pl.altkom.asc.lab.micronaut.poc.pricing.domain;

import java.util.Optional;

public interface Tariffs {

    Optional<Tariff> findByCode(String code);
    
    Tariff getByCode(String code);

    void add(Tariff tariff);
}
