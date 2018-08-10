package pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.adapters.web;

import io.micronaut.http.annotation.Controller;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.command.bus.CommandBus;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.OfferOperations;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createoffer.CreateOfferCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createoffer.CreateOfferResult;

@RequiredArgsConstructor
@Validated
@Controller("/offers")
public class OfferController implements OfferOperations {

    private final CommandBus bus;

    @Override
    public CreateOfferResult create(CreateOfferCommand cmd) {
        return bus.executeCommand(cmd);
    }
}
