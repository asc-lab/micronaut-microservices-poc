package pl.altkom.asc.lab.micronaut.poc.infrastructure.adapters.bus;

import io.micronaut.context.ApplicationContext;
import pl.altkom.asc.lab.micronaut.poc.command.bus.Registry;

import javax.inject.Singleton;

@Singleton
public class PolicySearchRegistry extends Registry {
    public PolicySearchRegistry(ApplicationContext applicationContext) {
        super(applicationContext);
    }
}
