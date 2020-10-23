package pl.altkom.asc.lab.micronaut.poc.dashboard.init;


import pl.altkom.asc.lab.micronaut.poc.dashboard.domain.LocalDateRange;
import pl.altkom.asc.lab.micronaut.poc.dashboard.domain.PolicyDocument;
import pl.altkom.asc.lab.micronaut.poc.dashboard.domain.PolicyRepository;
import pl.altkom.asc.lab.micronaut.poc.dashboard.infrastructure.adapters.elastic.ElasticHealthCheck;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class DataLoader implements ApplicationEventListener<ServerStartupEvent> {
    private final PolicyRepository policyRepository;
    private final ElasticHealthCheck elasticHealthCheck;

    @Override
    public void onApplicationEvent(ServerStartupEvent event) {
        waitForElasticSearch();
        savePolicies(generatePolicies());
    }

    private void waitForElasticSearch() {
        var retries = 0;
        while (retries<3) {
            try {
                var health = elasticHealthCheck.health();

                if (health.isOk())
                    return;
            } catch (Exception e) {

            }

            retries++;
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {

            }
        }

        throw new RuntimeException("Cannot connect to elastic search");
    }

    private void savePolicies(List<PolicyDocument> docs) {
        log.info("Docs to save " + docs.size());
        for (int i = 0; i < docs.size(); i++) {
            policyRepository.save(docs.get(i));
            if (i % 100 == 0) {
                log.info(i + " docs saved");
            }
        }
        log.info("Docs saved.");
    }

    private List<PolicyDocument> generatePolicies() {
        List<String> agents = Arrays.asList("jimmy.solid", "danny.solid", "admin", "agent1", "annn.wolf");
        List<String> products = Arrays.asList("TRI", "HSI", "FAI", "CAR");
        LocalDateRange generationPeriod = LocalDateRange.between(
                LocalDate.now().minusMonths(12),
                LocalDate.now()
        );
        return PolicyGenerator.builder()
                .agents(agents)
                .products(products)
                .generationPeriod(generationPeriod)
                .build()
                .generate();
    }
}
