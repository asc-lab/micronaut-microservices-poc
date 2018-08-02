package pl.altkom.asc.lab.micronaut.poc.policy.readmodel;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.PolicyClosedEvent;

@KafkaListener(clientId = "policy-closed-listener", offsetReset = OffsetReset.EARLIEST)
public class PolicyClosedListener extends AbstractPolicyListener {

    @Topic("policy-closed")
    void onPolicyClosed(PolicyClosedEvent event) {
        saveMappedPolicy(event.getPolicy());
    }
}
