package pl.altkom.asc.lab.micronaut.poc.command.bus;

import io.micronaut.context.ApplicationContext;

class CommandProvider<H extends CommandHandler<?, ?>> {

    private final ApplicationContext applicationContext;
    private final Class<H> type;

    CommandProvider(ApplicationContext applicationContext, Class<H> type) {
        this.applicationContext = applicationContext;
        this.type = type;
    }

    H get() {
        return applicationContext.getBean(type);
    }
}
