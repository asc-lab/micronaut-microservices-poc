package pl.altkom.asc.lab.micronaut.poc.command.bus;

import pl.altkom.asc.lab.micronaut.poc.command.bus.api.Command;

public interface CommandHandler<R, C extends Command<R>> {
    R handle(C command);
}
