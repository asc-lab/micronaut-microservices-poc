package pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.adapters.bus;

import io.micronaut.context.ApplicationContext;
import jakarta.inject.Singleton;
import pl.altkom.asc.lab.micronaut.poc.command.bus.Registry;

@Singleton
public class PolicyRegistry extends Registry {
    public PolicyRegistry(ApplicationContext applicationContext) {
        super(applicationContext);
    }
}
