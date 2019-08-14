package pl.altkom.asc.lab.micronaut.poc.payment.domain;

import io.micronaut.configuration.rabbitmq.annotation.Queue;
import io.micronaut.configuration.rabbitmq.annotation.RabbitListener;
import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.PolicyRegisteredEvent;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.dto.PolicyDto;

import java.util.Optional;

@RequiredArgsConstructor
@RabbitListener
public class PolicyRegisteredListener {

    private final PolicyAccountRepository policyAccountRepository;
    private final PolicyAccountNumberGenerator policyAccountNumberGenerator;

    @Queue("policy-registered")
    void onPolicyRegistered(PolicyRegisteredEvent event) {
        Optional<PolicyAccount> accountOpt = policyAccountRepository.findByPolicyNumber(event.getPolicy().getNumber());

        if (!accountOpt.isPresent())
            createAccount(event.getPolicy());
    }

    private void createAccount(PolicyDto policy) {
        policyAccountRepository.save(new PolicyAccount(policy.getNumber(), policyAccountNumberGenerator.generate()));
    }

}
