package pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.bus;

public interface CommandHandler<R, C extends  Command<R>> {
    R handle(C command);
}
