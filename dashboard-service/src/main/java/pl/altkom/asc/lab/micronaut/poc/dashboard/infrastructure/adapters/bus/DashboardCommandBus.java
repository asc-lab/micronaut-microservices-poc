package pl.altkom.asc.lab.micronaut.poc.dashboard.infrastructure.adapters.bus;

import pl.altkom.asc.lab.micronaut.poc.command.bus.MicronautCommandBus;
import pl.altkom.asc.lab.micronaut.poc.command.bus.Registry;

import javax.inject.Singleton;

@Singleton
public class DashboardCommandBus extends MicronautCommandBus {
    public DashboardCommandBus(Registry registry) {
        super(registry);
    }
}
