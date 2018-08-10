package pl.altkom.asc.lab.micronaut.poc.readmodel;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.PolicyRegisteredEvent;

@KafkaListener(clientId = "policy-registered-listener", offsetReset = OffsetReset.EARLIEST)
public class PolicyRegisteredListener extends AbstractPolicyListener {

    @Topic("policy-registered")
    void onPolicyRegistered(PolicyRegisteredEvent event) {
        saveMappedPolicy(event.getPolicy());
    }
}
