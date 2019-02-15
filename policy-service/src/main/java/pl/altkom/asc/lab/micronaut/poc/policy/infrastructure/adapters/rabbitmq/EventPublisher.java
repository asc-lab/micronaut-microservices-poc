package pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.adapters.rabbitmq;

import io.micronaut.configuration.rabbitmq.annotation.Binding;
import io.micronaut.configuration.rabbitmq.annotation.RabbitClient;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.PolicyRegisteredEvent;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.PolicyTerminatedEvent;

@RabbitClient("micronaut-microservices-poc")
public interface EventPublisher {

    @Binding("policy-registered")
    void policyRegisteredEvent(PolicyRegisteredEvent event);

    @Binding("policy-terminated")
    void policyTerminatedEvent(PolicyTerminatedEvent event);
}
