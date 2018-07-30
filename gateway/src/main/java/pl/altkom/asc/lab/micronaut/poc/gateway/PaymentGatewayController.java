package pl.altkom.asc.lab.micronaut.poc.gateway;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import pl.altkom.asc.lab.micronaut.poc.gateway.client.v1.PaymentGatewayClient;
import pl.altkom.asc.lab.micronaut.poc.payment.service.api.v1.PolicyAccountDto;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collection;

@Singleton
@Controller("/payment")
public class PaymentGatewayController {

    @Inject
    private PaymentGatewayClient paymentClient;

    @Get("/accounts")
    Collection<PolicyAccountDto> accounts() {
        return paymentClient.accounts();
    }
}
