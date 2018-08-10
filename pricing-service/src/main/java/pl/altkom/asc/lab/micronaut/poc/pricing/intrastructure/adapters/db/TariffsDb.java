package pl.altkom.asc.lab.micronaut.poc.pricing.intrastructure.adapters.db;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;
import pl.altkom.asc.lab.micronaut.poc.pricing.domain.Tariff;
import pl.altkom.asc.lab.micronaut.poc.pricing.domain.Tariffs;

import javax.inject.Singleton;
import java.util.Optional;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Singleton
public class TariffsDb implements Tariffs {

    @Inject
    @CurrentSession
    EntityManager entityManager;

    @Transactional
    @Override
    public Optional<Tariff> findByCode(String code) {
        return entityManager
                .createQuery("from Tariff t where t.code = :code", Tariff.class)
                .setParameter("code", code)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Tariff getByCode(String code) {
        return findByCode(code).orElseThrow(() -> new RuntimeException("Tariff not found"));
    }

    @Transactional
    @Override
    public void add(Tariff tariff) {
        entityManager.persist(tariff);
    }

    
}
