package pl.altkom.asc.lab.micronaut.poc.policy.commands;

import jakarta.inject.Singleton;
import java.time.LocalDate;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.command.bus.CommandHandler;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.AgentRef;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.Offer;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.OfferRepository;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.Person;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.Policy;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.PolicyFactory;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.PolicyRepository;
import pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.adapters.kafka.EventPublisher;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createpolicy.CreatePolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createpolicy.CreatePolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.PolicyRegisteredEvent;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.dto.PolicyDto;

@Singleton
@RequiredArgsConstructor
public class CreatePolicyHandler implements CommandHandler<CreatePolicyResult, CreatePolicyCommand> {

    private final PolicyRepository policyRepository;
    private final OfferRepository offerRepository;
    private final PolicyFactory policyFactory = new PolicyFactory();
    private final EventPublisher eventPublisher;

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
        AgentRef agent = AgentRef.of(cmd.getAgentLogin());
        Policy policy = policyFactory.fromOffer(offer, policyHolder, agent);

        //save policy and update offer
        policyRepository.save(policy);

        //publish events
        eventPublisher.policyRegisteredEvent(policy.getNumber(), createEvent(policy));

        return new CreatePolicyResult(policy.getNumber());
    }

    private PolicyRegisteredEvent createEvent(Policy policy) {
        return new PolicyRegisteredEvent(
                new PolicyDto(
                        policy.getNumber(),
                        policy.versions().lastVersion().getVersionValidityPeriod().getFrom(),
                        policy.versions().lastVersion().getVersionValidityPeriod().getTo(),
                        policy.versions().lastVersion().getPolicyHolder().getFullName(),
                        policy.versions().lastVersion().getProductCode(),
                        policy.versions().lastVersion().getTotalPremiumAmount(),
                        null
                )
        );
    }
}
