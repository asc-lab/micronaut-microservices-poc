package pl.altkom.asc.lab.micronaut.poc.command.bus;

import io.micronaut.context.ApplicationContext;

class QueryProvider<H extends QueryHandler<?, ?>> {

    private final ApplicationContext applicationContext;
    private final Class<H> type;

    QueryProvider(ApplicationContext applicationContext, Class<H> type) {
        this.applicationContext = applicationContext;
        this.type = type;
    }

    H get() {
        return applicationContext.getBean(type);
    }
}
