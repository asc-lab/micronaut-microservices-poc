package pl.altkom.asc.lab.micronaut.poc.auth;

import java.util.Arrays;
import java.util.UUID;

import javax.inject.Singleton;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class DataLoader implements ApplicationEventListener<ServerStartupEvent> {
    private final InsuranceAgentsRepository insuranceAgentsRepository;

    @Override
    public void onApplicationEvent(ServerStartupEvent event) {
        if (!insuranceAgentsRepository.findByLogin("admin").isPresent()) {
            insuranceAgentsRepository.save(new InsuranceAgent(UUID.randomUUID(), "admin", "admin", "static/avatars/admin.png", Arrays.asList("TRI", "HSI")));
        }

        if (!insuranceAgentsRepository.findByLogin("jimmy.solid").isPresent()) {
            insuranceAgentsRepository.save(new InsuranceAgent(UUID.randomUUID(), "jimmy.solid", "secret", "static/avatars/jimmy_solid.png", Arrays.asList("TRI", "HSI", "FAI", "CAR")));
        }

        if (!insuranceAgentsRepository.findByLogin("danny.solid").isPresent()) {
            insuranceAgentsRepository.save(new InsuranceAgent(UUID.randomUUID(),"danny.solid", "secret", "static/avatars/danny_solid.png", Arrays.asList("TRI", "HSI")));
        }

        if (!insuranceAgentsRepository.findByLogin("agent1").isPresent()) {
            insuranceAgentsRepository.save(new InsuranceAgent(UUID.randomUUID(),"agent1", "agent1", "static/avatars/agent1.png", Arrays.asList("TRI", "HSI")));
        }
    }
}
