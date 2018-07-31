package pl.altkom.asc.lab.micronaut.poc.pricing.intrastructure.adapters.db;

import pl.altkom.asc.lab.micronaut.poc.pricing.domain.Tariff;
import pl.altkom.asc.lab.micronaut.poc.pricing.domain.Tariffs;
import pl.altkom.asc.lab.micronaut.poc.pricing.init.DemoTariffsFactory;

import javax.inject.Singleton;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class TariffsDb implements Tariffs {
    private final Map<String, Tariff> inMemoDb = new ConcurrentHashMap<>();

    public TariffsDb() {
        inMemoDb.put("HSI", DemoTariffsFactory.house());
        inMemoDb.put("TRI", DemoTariffsFactory.tourist());
    }

    @Override
    public Tariff findByCode(String code) {
        return inMemoDb.get(code);
    }

    @Override
    public void add(Tariff tariff) {
        inMemoDb.put(tariff.getCode(), tariff);
    }
}
