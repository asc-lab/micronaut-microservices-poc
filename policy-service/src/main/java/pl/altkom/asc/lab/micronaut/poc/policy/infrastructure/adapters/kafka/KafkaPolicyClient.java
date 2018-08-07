package pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.adapters.kafka;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.PolicyTerminatedEvent;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.PolicyRegisteredEvent;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.policyregistered.PolicyRegisteredApiEvent;

@KafkaClient
public interface KafkaPolicyClient {

    @Topic("policy-registered-outside")
    void policyRegisteredOutsideEvent(@KafkaKey String policyNumber, PolicyRegisteredApiEvent event);

    @Topic("policy-registered-inside")
    void policyRegisteredEvent(@KafkaKey String policyNumber, PolicyRegisteredEvent event);

    @Topic("policy-terminated")
    void policyTerminatedEvent(@KafkaKey String policyNumber, PolicyTerminatedEvent event);
}
