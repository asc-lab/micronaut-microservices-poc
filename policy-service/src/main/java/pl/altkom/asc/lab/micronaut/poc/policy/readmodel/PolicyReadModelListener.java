package pl.altkom.asc.lab.micronaut.poc.policy.readmodel;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.Policy;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.PolicyClosedEvent;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.PolicyRegisteredEvent;

@KafkaListener(offsetReset = OffsetReset.EARLIEST)
@RequiredArgsConstructor
public class PolicyReadModelListener {

    private final PolicyViewRepository policyViewRepository;

    @Topic("policy-registered")
    void onPolicyRegistered(PolicyRegisteredEvent event) {
        saveMappedPolicy(event.getPolicy());
    }

    @Topic("policy-closed")
    void onPolicyClosed(PolicyClosedEvent event) {
        saveMappedPolicy(event.getPolicy());
    }

    private void saveMappedPolicy(Policy policy) {
        PolicyView view = PolicyViewAssembler.map(policy);
        policyViewRepository.save(view);
    }
}
