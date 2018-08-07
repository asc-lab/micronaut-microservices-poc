package pl.altkom.asc.lab.micronaut.poc.policy.commands;

import io.micronaut.spring.tx.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.adapters.kafka.KafkaPolicyClient;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.*;
import pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.bus.CommandHandler;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.policyregistered.dto.PolicyDto;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createpolicy.CreatePolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createpolicy.CreatePolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.policyregistered.PolicyRegisteredApiEvent;

import javax.inject.Singleton;
import java.time.LocalDate;

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
