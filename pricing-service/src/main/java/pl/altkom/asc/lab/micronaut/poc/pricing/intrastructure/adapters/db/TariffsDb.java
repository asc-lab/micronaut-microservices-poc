package pl.altkom.asc.lab.micronaut.poc.pricing.intrastructure.adapters.db;

import io.micronaut.spring.tx.annotation.Transactional;
import pl.altkom.asc.lab.micronaut.poc.pricing.domain.Tariff;
import pl.altkom.asc.lab.micronaut.poc.pricing.domain.Tariffs;

import javax.inject.Singleton;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Singleton
public class TariffsDb implements Tariffs {
    private final SessionFactory sessionFactory;

    public TariffsDb(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public Optional<Tariff> findByCode(String code) {
        return currentSession()
                .createQuery("from Tariff t where t.code = :code", Tariff.class)
                .setParameter("code", code)
                .uniqueResultOptional();
    }

    @Override
    public Tariff getByCode(String code) {
        return findByCode(code).orElseThrow(() -> new RuntimeException("Tariff not found"));
    }

    @Transactional
    @Override
    public void add(Tariff tariff) {
        currentSession().save(tariff);
    }
    
    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
}
