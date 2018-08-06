package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import javax.validation.constraints.NotNull;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createoffer.CreateOfferCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createoffer.CreateOfferResult;

public interface OfferOperations {
    @Post("/")
    CreateOfferResult create(@Body @NotNull CreateOfferCommand cmd);
}
