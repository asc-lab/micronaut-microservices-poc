package pl.altkom.asc.lab.micronaut.poc.policy.commands.policyregister;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.PolicyRegisteredEvent;

@KafkaClient
public interface RegisterPolicyClient {

    @Topic("policy-registered")
    void policyRegisteredEvent(@KafkaKey String policyNumber, PolicyRegisteredEvent event);
}
