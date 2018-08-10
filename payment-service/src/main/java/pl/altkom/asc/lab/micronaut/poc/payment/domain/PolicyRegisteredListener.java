package pl.altkom.asc.lab.micronaut.poc.payment.domain;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.dto.PolicyDto;


import java.util.Optional;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.PolicyRegisteredEvent;

@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class PolicyRegisteredListener {

    private final PolicyAccountRepository policyAccountRepository;
    private final PolicyAccountNumberGenerator policyAccountNumberGenerator;

    public PolicyRegisteredListener(PolicyAccountRepository policyAccountRepository, PolicyAccountNumberGenerator policyAccountNumberGenerator) {
        this.policyAccountRepository = policyAccountRepository;
        this.policyAccountNumberGenerator = policyAccountNumberGenerator;
    }

    @Topic("policy-registered")
    void onPolicyRegistered(PolicyRegisteredEvent event) {
        Optional<PolicyAccount> accountOpt = policyAccountRepository.findForPolicy(event.getPolicy().getNumber());

        if (!accountOpt.isPresent())
            createAccount(event.getPolicy());
    }

    private void createAccount(PolicyDto policy) {
        policyAccountRepository.add(new PolicyAccount(policy.getNumber(), policyAccountNumberGenerator.generate()));
    }

}
