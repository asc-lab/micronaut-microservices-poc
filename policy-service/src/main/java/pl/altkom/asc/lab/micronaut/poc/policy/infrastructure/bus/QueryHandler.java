package pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.bus;

public interface QueryHandler<R, C extends Query<R>> {
    R handle(C var1);
}
