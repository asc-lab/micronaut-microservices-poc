package pl.altkom.asc.lab.micronaut.poc.command.bus;

import pl.altkom.asc.lab.micronaut.poc.command.bus.api.Query;

public interface QueryHandler<R, C extends Query<R>> {
    R handle(C var1);
}
