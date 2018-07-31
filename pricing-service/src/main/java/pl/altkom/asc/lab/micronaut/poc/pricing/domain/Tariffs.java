package pl.altkom.asc.lab.micronaut.poc.pricing.domain;

public interface Tariffs {
    Tariff findByCode(String code);

    void add(Tariff tariff);
}
