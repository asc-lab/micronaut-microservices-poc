package pl.altkom.asc.lab.micronaut.poc.gateway;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import pl.altkom.asc.lab.micronaut.poc.gateway.client.v1.PolicyGatewayClient;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createoffer.CreateOfferCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createoffer.CreateOfferResult;

import javax.inject.Inject;

@Controller("/api/offers")
public class OfferGatewayController {

    @Inject
    private PolicyGatewayClient client;

    @Post("/")
    CreateOfferResult create(@Body CreateOfferCommand cmd) {
        return client.createOffer(cmd);
    }
}
