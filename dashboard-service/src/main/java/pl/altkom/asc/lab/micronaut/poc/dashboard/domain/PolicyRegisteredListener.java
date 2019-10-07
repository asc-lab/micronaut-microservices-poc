package pl.altkom.asc.lab.micronaut.poc.dashboard.domain;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.PolicyRegisteredEvent;

@KafkaListener(clientId = "policy-registered-dashboard-listener", offsetReset = OffsetReset.EARLIEST)
@RequiredArgsConstructor
public class PolicyRegisteredListener {

    private final PolicyRepository policyRepository;

    @Topic("policy-registered")
    void onPolicyRegistered(PolicyRegisteredEvent event) {
        policyRepository.save(new PolicyDocument(
                event.getPolicy().getNumber(),
                event.getPolicy().getFrom(),
                event.getPolicy().getTo(),
                event.getPolicy().getPolicyHolder(),
                event.getPolicy().getProductCode(),
                event.getPolicy().getTotalPremium(),
                event.getPolicy().getAgentLogin()
        ));
    }
}
