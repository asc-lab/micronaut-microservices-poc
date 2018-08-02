package pl.altkom.asc.lab.micronaut.poc.policy.init;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.policy.readmodel.PolicyView;
import pl.altkom.asc.lab.micronaut.poc.policy.readmodel.PolicyViewRepository;

import javax.inject.Singleton;
import java.time.LocalDate;
import java.util.List;

@Singleton
@RequiredArgsConstructor
public class DataLoader implements ApplicationEventListener<ServerStartupEvent> {

    private final PolicyViewRepository policyViewRepository;

    @Override
    public void onApplicationEvent(ServerStartupEvent serverStartupEvent) {
        List<PolicyView> policies = policyViewRepository.findAll();

        if (policies.stream().noneMatch(p -> p.getNumber().equals("P1"))) {
            policyViewRepository.save(new PolicyView(1L,
                    "P1",
                    LocalDate.of(2018, 8, 1),
                    LocalDate.of(2019, 7, 31),
                    "Robert Witkowski")
            );
        }

        if (policies.stream().noneMatch(p -> p.getNumber().equals("P2"))) {
            policyViewRepository.save(new PolicyView(2L,
                    "P2",
                    LocalDate.of(2018, 7, 1),
                    LocalDate.of(2019, 6, 30),
                    "John Smith")
            );
        }

    }
}