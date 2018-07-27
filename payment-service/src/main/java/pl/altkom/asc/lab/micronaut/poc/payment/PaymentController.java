package pl.altkom.asc.lab.micronaut.poc.payment;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import pl.altkom.asc.lab.micronaut.poc.payment.domain.PolicyAccount;
import pl.altkom.asc.lab.micronaut.poc.payment.domain.PolicyAccountRepository;

import java.util.Collection;

@Controller("/payment")
public class PaymentController {

    private final PolicyAccountRepository policyAccountRepository;

    public PaymentController(PolicyAccountRepository policyAccountRepository) {
        this.policyAccountRepository = policyAccountRepository;
    }

    @Get("/accounts")
    public Collection<PolicyAccount> accounts() {
        return policyAccountRepository.findAll();
    }
}
