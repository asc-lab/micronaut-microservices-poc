package pl.altkom.asc.lab.micronaut.poc.payment.service.api.v1.operations;

import io.micronaut.http.annotation.Get;
import pl.altkom.asc.lab.micronaut.poc.payment.service.api.v1.PolicyAccountDto;

import java.util.Collection;

public interface PaymentOperations {

    @Get("/accounts")
    Collection<PolicyAccountDto> accounts();
}
