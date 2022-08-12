package pl.altkom.asc.lab.micronaut.poc.payment.domain;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.PolicyRegisteredEvent;
import static org.awaitility.Awaitility.await;

@MicronautTest
public class PolicyRegisteredListenerTest {

  @Inject
  PolicyRegisteredPublisher policyRegisteredPublisher;
  
  @Inject
  PolicyAccountRepository policyAccounts;
  
  @Test
  void policyAccountIsCreatedWhenPolicyRegisteredEventIsReceived() {
    var policyCreatedEvent = new PolicyRegisteredEvent(PolicyDtoObjectMother.samplePolicyWithNumber("1234"));
    
    policyRegisteredPublisher.policyRegisteredEvent("1234", policyCreatedEvent);
    
    await()
        .atMost(15, TimeUnit.SECONDS)
        .until(() -> {
          var account = policyAccounts.findByPolicyNumber("1234");
          return account.isPresent();
        });
  }
  
}
