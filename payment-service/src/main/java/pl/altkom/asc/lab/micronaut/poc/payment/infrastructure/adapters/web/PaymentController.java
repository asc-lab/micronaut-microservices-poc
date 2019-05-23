package pl.altkom.asc.lab.micronaut.poc.payment.infrastructure.adapters.web;

import io.micronaut.configuration.hystrix.annotation.HystrixCommand;
import io.micronaut.http.annotation.Controller;
import java.time.LocalDate;
import pl.altkom.asc.lab.micronaut.poc.payment.service.api.v1.PolicyAccountDto;
import pl.altkom.asc.lab.micronaut.poc.payment.service.api.v1.operations.PaymentOperations;
import pl.altkom.asc.lab.micronaut.poc.payment.domain.PolicyAccountRepository;

import java.util.Collection;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.payment.service.api.v1.PolicyAccountBalanceDto;
import pl.altkom.asc.lab.micronaut.poc.payment.service.api.v1.exceptions.PolicyAccountNotFound;

@Controller("/payment")
@RequiredArgsConstructor
public class PaymentController implements PaymentOperations {

    private final PolicyAccountRepository policyAccountRepository;

    @Override
    @HystrixCommand
    public Collection<PolicyAccountDto> accounts() {
        return policyAccountRepository.findAll().stream()
                .map(account -> new PolicyAccountDto(account.getPolicyNumber(), account.getPolicyAccountNumber()))
                .collect(Collectors.toList());
    }
    
    @Override
    @HystrixCommand
    public PolicyAccountBalanceDto accountBalance(String accountNumber) {
        return policyAccountRepository.findByNumber(accountNumber)
                .map(account -> new PolicyAccountBalanceDto(
                        account.getPolicyNumber(),
                        account.getPolicyAccountNumber(),
                        account.balanceAt(LocalDate.now())))
                .orElseThrow(() -> new PolicyAccountNotFound(accountNumber));
    }
}
