package pl.altkom.asc.lab.micronaut.poc.payment.domain;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import pl.altkom.asc.lab.micronaut.poc.api.v1.PolicyDto;
import pl.altkom.asc.lab.micronaut.poc.api.v1.PolicyRegisteredApiEvent;

import java.util.Optional;

@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class PolicyRegisteredListener {

    private final PolicyAccountRepository policyAccountRepository;

    public PolicyRegisteredListener(PolicyAccountRepository policyAccountRepository) {
        this.policyAccountRepository = policyAccountRepository;
    }

    @Topic("policy-registered-outside")
    void onPolicyRegistered(PolicyRegisteredApiEvent event) {
        Optional<PolicyAccount> accountOpt = policyAccountRepository.findForPolicy(event.getPolicy().getPolicyNumber());

        if (!accountOpt.isPresent())
            createAccount(event.getPolicy());
    }

    private void createAccount(PolicyDto policy) {
        policyAccountRepository.save(new PolicyAccount(policy.getPolicyNumber()));
    }

}
