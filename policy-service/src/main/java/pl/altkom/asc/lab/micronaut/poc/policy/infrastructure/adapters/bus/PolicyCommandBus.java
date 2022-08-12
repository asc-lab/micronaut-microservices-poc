package pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.adapters.bus;

import jakarta.inject.Singleton;
import pl.altkom.asc.lab.micronaut.poc.command.bus.MicronautCommandBus;
import pl.altkom.asc.lab.micronaut.poc.command.bus.Registry;

@Singleton
public class PolicyCommandBus extends MicronautCommandBus {
    public PolicyCommandBus(Registry registry) {
        super(registry);
    }
}
