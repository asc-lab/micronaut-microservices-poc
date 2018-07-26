package pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.bus;

import io.micronaut.context.ApplicationContext;

public class QueryProvider<H extends QueryHandler<?, ?>> {

    private final ApplicationContext applicationContext;
    private final Class<H> type;

    QueryProvider(ApplicationContext applicationContext, Class<H> type) {
        this.applicationContext = applicationContext;
        this.type = type;
    }

    public H get() {
        return applicationContext.getBean(type);
    }
}
