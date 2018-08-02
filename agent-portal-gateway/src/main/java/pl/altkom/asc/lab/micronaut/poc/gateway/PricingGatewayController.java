package pl.altkom.asc.lab.micronaut.poc.gateway;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.http.hateos.JsonError;
import io.micronaut.http.hateos.Link;
import pl.altkom.asc.lab.micronaut.poc.gateway.client.v1.PricingGatewayClient;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.CalculatePriceCommand;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.CalculatePriceResult;

import javax.inject.Inject;

@Controller("/api/pricing")
public class PricingGatewayController {

    @Inject
    private PricingGatewayClient pricingClient;

    @Post("/price")
    CalculatePriceResult calculatePrice(CalculatePriceCommand cmd) {
        return pricingClient.calculatePrice(cmd);
    }

    @Error
    public HttpResponse<JsonError> error(HttpRequest request, HttpClientResponseException e) {
        JsonError error = new JsonError("Bad Things Happened: " + e.getMessage())
                .link(Link.SELF, Link.of(request.getUri()));

        return HttpResponse.<JsonError>serverError().body(error);
    }
}
