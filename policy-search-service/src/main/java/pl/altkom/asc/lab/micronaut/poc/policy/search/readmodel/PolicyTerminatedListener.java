package pl.altkom.asc.lab.micronaut.poc.policy.search.readmodel;

import io.micronaut.configuration.rabbitmq.annotation.Queue;
import io.micronaut.configuration.rabbitmq.annotation.RabbitListener;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.PolicyTerminatedEvent;

@RabbitListener
public class PolicyTerminatedListener extends AbstractPolicyListener {

    @Queue("policy-terminated")
    void onPolicyTerminated(PolicyTerminatedEvent event) {
        saveMappedPolicy(event.getPolicy());
    }
}
