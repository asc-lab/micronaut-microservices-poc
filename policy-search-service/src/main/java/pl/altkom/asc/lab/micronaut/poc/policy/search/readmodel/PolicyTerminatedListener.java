package pl.altkom.asc.lab.micronaut.poc.policy.search.readmodel;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.PolicyTerminatedEvent;

@KafkaListener(clientId = "policy-terminated-listener", offsetReset = OffsetReset.EARLIEST)
public class PolicyTerminatedListener extends AbstractPolicyListener {

    @Topic("policy-terminated")
    void onPolicyTerminated(PolicyTerminatedEvent event) {
        saveMappedPolicy(event.getPolicy());
    }
}
