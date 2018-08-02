package pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.bus;

import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.Command;

public interface CommandHandler<R, C extends Command<R>> {
    R handle(C command);
}
