package pl.altkom.asc.lab.micronaut.poc.command.bus;

import pl.altkom.asc.lab.micronaut.poc.command.bus.api.Command;
import pl.altkom.asc.lab.micronaut.poc.command.bus.api.Query;

public interface CommandBus {
    <R, C extends Command<R>> R executeCommand(C command);

    <R, Q extends Query<R>> R executeQuery(Q query);
}
