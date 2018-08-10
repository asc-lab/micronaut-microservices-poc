package pl.altkom.asc.lab.micronaut.poc.policy.search.infrastructure.adapters.bus;

import pl.altkom.asc.lab.micronaut.poc.command.bus.MicronautCommandBus;
import pl.altkom.asc.lab.micronaut.poc.command.bus.Registry;

import javax.inject.Singleton;

@Singleton
public class PolicySearchCommandBus extends MicronautCommandBus {
    public PolicySearchCommandBus(Registry registry) {
        super(registry);
    }
}
