package pl.altkom.asc.lab.micronaut.poc.payment.domain;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.PolicyRegisteredEvent;

@KafkaClient
public interface PolicyRegisteredPublisher {
  
  @Topic("policy-registered")
  void policyRegisteredEvent(
      @KafkaKey
      String policyNumber, PolicyRegisteredEvent event);
}
