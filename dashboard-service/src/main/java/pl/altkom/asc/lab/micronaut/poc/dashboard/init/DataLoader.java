package pl.altkom.asc.lab.micronaut.poc.dashboard.init;


import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.altkom.asc.lab.micronaut.poc.dashboard.domain.LocalDateRange;
import pl.altkom.asc.lab.micronaut.poc.dashboard.domain.PolicyDocument;
import pl.altkom.asc.lab.micronaut.poc.dashboard.domain.PolicyRepository;

import javax.inject.Singleton;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class DataLoader implements ApplicationEventListener<ServerStartupEvent> {

    private final PolicyRepository policyRepository;

    @Override
    public void onApplicationEvent(ServerStartupEvent event) {
        List<String> agents = Arrays.asList("jimmy.solid", "danny.solid", "admin", "agent1", "annn.wolf");
        List<String> products = Arrays.asList("TRI", "HSI", "FAI", "CAR");
        LocalDateRange generationPeriod = LocalDateRange.between(
                LocalDate.now().minusMonths(12),
                LocalDate.now()
        );
        List<PolicyDocument> docs = PolicyGenerator.builder()
                .agents(agents)
                .products(products)
                .generationPeriod(generationPeriod)
                .build()
                .generate();

        log.info("Docs to save " + docs.size());
        for (int i = 0; i < docs.size(); i++) {
            policyRepository.save(docs.get(i));
            if (i % 100 == 0) {
                log.info(i + " docs saved");
            }
        }
        log.info("Docs saved.");
    }
}
