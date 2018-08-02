package pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.bus;

import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.Query;

public interface QueryHandler<R, C extends Query<R>> {
    R handle(C var1);
}
