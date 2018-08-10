package pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.adapters.kafka;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.PolicyRegisteredEvent;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.PolicyTerminatedEvent;

@KafkaClient
public interface EventPublisher {

    @Topic("policy-registered")
    void policyRegisteredEvent(@KafkaKey String policyNumber, PolicyRegisteredEvent event);

    @Topic("policy-terminated")
    void policyTerminatedEvent(@KafkaKey String policyNumber, PolicyTerminatedEvent event);
}
