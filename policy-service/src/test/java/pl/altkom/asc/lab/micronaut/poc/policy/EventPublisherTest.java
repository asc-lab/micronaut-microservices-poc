package pl.altkom.asc.lab.micronaut.poc.policy;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.adapters.kafka.EventPublisher;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.PolicyRegisteredEvent;
import static org.awaitility.Awaitility.await;

@MicronautTest
public class EventPublisherTest {
  
  private static final Collection<PolicyRegisteredEvent> sentEvents = new ConcurrentLinkedDeque<>();
  
  @Inject
  private EventPublisher eventPublisher;
  
  @Test
  void canSendEvent() {
    var policyEvent = new PolicyRegisteredEvent(PolicyDtoObjectMother.samplePolicyWithNumber("123"));
    eventPublisher.policyRegisteredEvent("123", policyEvent);
    
    await()
        .atMost(5, TimeUnit.SECONDS)
        .until(()-> sentEvents.size()==1 && sentEvents.contains(policyEvent));
  }
  
  @KafkaListener(offsetReset = OffsetReset.EARLIEST)
  public static class PolicyRegisteredTestListener {
    @Topic("policy-registered")
    void onPolicyRegistered(PolicyRegisteredEvent event) {
      sentEvents.add(event);
    }
  }
}
