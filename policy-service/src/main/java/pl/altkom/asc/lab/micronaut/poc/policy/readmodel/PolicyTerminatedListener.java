package pl.altkom.asc.lab.micronaut.poc.policy.readmodel;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.PolicyTerminatedEvent;

@KafkaListener(clientId = "policy-closed-listener", offsetReset = OffsetReset.EARLIEST)
public class PolicyTerminatedListener extends AbstractPolicyListener {

    @Topic("policy-closed")
    void onPolicyClosed(PolicyTerminatedEvent event) {
        saveMappedPolicy(event.getPolicy());
    }
}
