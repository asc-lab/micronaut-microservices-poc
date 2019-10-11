package pl.altkom.asc.lab.micronaut.poc.dashboard.infrastructure.adapters.bus;

import io.micronaut.context.ApplicationContext;
import pl.altkom.asc.lab.micronaut.poc.command.bus.Registry;

import javax.inject.Singleton;

@Singleton
public class DashboardRegistry extends Registry {
    public DashboardRegistry(ApplicationContext applicationContext) {
        super(applicationContext);
    }
}
