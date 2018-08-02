package pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.bus;

import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.Command;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.Query;

public interface CommandBus {
    <R,C extends Command<R>> R executeCommand(C command);
    <R,Q extends Query<R>> R executeQuery(Q query);
}
