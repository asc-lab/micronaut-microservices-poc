package pl.altkom.asc.lab.micronaut.poc.policy.commands.policyclose;

import pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.bus.CommandHandler;

import javax.inject.Singleton;

@Singleton
public class ClosePolicyHandler implements CommandHandler<ClosePolicyResult, ClosePolicyCommand> {

    @Override
    public ClosePolicyResult handle(ClosePolicyCommand command) {
        return null;
    }
}
