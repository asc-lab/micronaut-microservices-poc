package pl.altkom.asc.lab.micronaut.poc.policy.search.readmodel;

import com.rabbitmq.client.BasicProperties;
import com.rabbitmq.client.Envelope;
import io.micronaut.configuration.rabbitmq.annotation.Queue;
import io.micronaut.configuration.rabbitmq.annotation.RabbitListener;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.PolicyRegisteredEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RabbitListener
public class PolicyRegisteredListener extends AbstractPolicyListener {

    private List<String> messages = Collections.synchronizedList(new ArrayList<>());

    @Queue("policy-registered")
    void onPolicyRegistered(PolicyRegisteredEvent event,
                            Envelope envelope,
                            BasicProperties basicProperties) {
        saveMappedPolicy(event.getPolicy());
        String msg = String.format("exchange: [%s], routingKey: [%s], contentType: [%s]",
                envelope.getExchange(), envelope.getRoutingKey(), basicProperties.getContentType());
        messages.add(msg);
    }
}
