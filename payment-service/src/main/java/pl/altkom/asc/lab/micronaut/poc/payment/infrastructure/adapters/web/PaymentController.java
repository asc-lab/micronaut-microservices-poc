package pl.altkom.asc.lab.micronaut.poc.payment.infrastructure.adapters.web;

import io.micronaut.configuration.hystrix.annotation.HystrixCommand;
import io.micronaut.http.annotation.Controller;
import pl.altkom.asc.lab.micronaut.poc.payment.service.api.v1.PolicyAccountDto;
import pl.altkom.asc.lab.micronaut.poc.payment.service.api.v1.operations.PaymentOperations;
import pl.altkom.asc.lab.micronaut.poc.payment.domain.PolicyAccountRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Controller("/payment")
public class PaymentController implements PaymentOperations {

    private final PolicyAccountRepository policyAccountRepository;

    public PaymentController(PolicyAccountRepository policyAccountRepository) {
        this.policyAccountRepository = policyAccountRepository;
    }

    @Override
    @HystrixCommand
    public Collection<PolicyAccountDto> accounts() {
        return policyAccountRepository.findAll().stream()
                .map(x -> new PolicyAccountDto(x.getPolicyNumber(), x.getPolicyAccountNumber()))
                .collect(Collectors.toList());
    }
}
