package pl.altkom.asc.lab.micronaut.poc.policy.commands;

import io.micronaut.spring.tx.annotation.Transactional;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.policy.client.kafka.KafkaPolicyClient;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.Policy;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.PolicyFactory;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.PolicyRegisteredEvent;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.PolicyRepository;
import pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.bus.CommandHandler;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.PolicyDto;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.PolicyRegisteredApiEvent;

import javax.inject.Singleton;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.Offer;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.OfferRepository;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.Person;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createpolicy.CreatePolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createpolicy.CreatePolicyResult;

@Singleton
@RequiredArgsConstructor
public class CreatePolicyHandler implements CommandHandler<CreatePolicyResult, CreatePolicyCommand> {

    private final PolicyRepository policyRepository;
    private final OfferRepository offerRepository;
    private final PolicyFactory policyFactory = new PolicyFactory();
    private final KafkaPolicyClient policyClient;

    @Transactional
    @Override
    public CreatePolicyResult handle(CreatePolicyCommand cmd) {
        //get offer
        Offer offer = offerRepository.getByNumber(cmd.getOfferNumber());
        
        //if offer not expired and not already converted
        if (offer.isExpired(LocalDate.now())) {
            throw new RuntimeException("Offer has expired");
        }
        
        //create policy from offer
        Person policyHolder = new Person(cmd.getPolicyHolder().getFirstName(), cmd.getPolicyHolder().getLastName(), cmd.getPolicyHolder().getTaxId());
        Policy policy = policyFactory.fromOffer(offer, policyHolder);
        
        //save policy and update offer
        policyRepository.add(policy);
        
        //publish events
        policyClient.policyRegisteredEvent(policy.getNumber(), new PolicyRegisteredEvent(policy));
        policyClient.policyRegisteredOutsideEvent(policy.getNumber(), new PolicyRegisteredApiEvent(new PolicyDto(policy.getNumber())));
        
        return new CreatePolicyResult(policy.getNumber());
    }
}
