package pl.altkom.asc.lab.micronaut.poc.pricing.init;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import io.micronaut.spring.tx.annotation.Transactional;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.pricing.intrastructure.adapters.db.TariffsDb;

@Singleton
@RequiredArgsConstructor
public class DataLoader implements ApplicationEventListener<ServerStartupEvent> {

    private final TariffsDb tariffsDb;

    @Transactional
    @Override
    public void onApplicationEvent(ServerStartupEvent event) {
        if (!tariffsDb.findByCode("HSI").isPresent()) {
            tariffsDb.add(DemoTariffsFactory.house());
        }
        
        if (!tariffsDb.findByCode("TRI").isPresent()) {
            tariffsDb.add(DemoTariffsFactory.travel());
        }

        if (!tariffsDb.findByCode("FAI").isPresent()) {
            tariffsDb.add(DemoTariffsFactory.farm());
        }

        if (!tariffsDb.findByCode("CAR").isPresent()) {
            tariffsDb.add(DemoTariffsFactory.car());
        }
    }
}
