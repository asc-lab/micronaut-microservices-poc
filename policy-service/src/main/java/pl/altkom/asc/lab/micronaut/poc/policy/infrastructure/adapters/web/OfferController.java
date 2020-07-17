package pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.adapters.web;

import pl.altkom.asc.lab.micronaut.poc.command.bus.CommandBus;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.OfferOperations;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createoffer.CreateOfferCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createoffer.CreateOfferResult;

import io.micronaut.http.annotation.Controller;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Validated
@Controller("/offers")
public class OfferController implements OfferOperations {

    private final CommandBus bus;

    @ExecuteOn(TaskExecutors.IO)
    @Override
    public CreateOfferResult create(CreateOfferCommand cmd) {
        return bus.executeCommand(cmd);
    }
}
