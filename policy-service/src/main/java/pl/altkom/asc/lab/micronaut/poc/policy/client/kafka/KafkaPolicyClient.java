package pl.altkom.asc.lab.micronaut.poc.policy.client.kafka;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.PolicyClosedEvent;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.PolicyRegisteredEvent;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.PolicyRegisteredApiEvent;

@KafkaClient
public interface KafkaPolicyClient {

    @Topic("policy-registered-outside")
    void policyRegisteredOutsideEvent(@KafkaKey String policyNumber, PolicyRegisteredApiEvent event);

    @Topic("policy-registered")
    void policyRegisteredEvent(@KafkaKey String policyNumber, PolicyRegisteredEvent event);

    @Topic("policy-closed")
    void policyClosedEvent(@KafkaKey String policyNumber, PolicyClosedEvent event);
}
