package pl.altkom.asc.lab.micronaut.poc.policy.commands;

import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.command.bus.CommandHandler;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.Policy;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.PolicyRepository;
import pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.adapters.kafka.EventPublisher;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.terminatepolicy.TerminatePolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.terminatepolicy.TerminatePolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.PolicyTerminatedEvent;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.dto.PolicyDto;
import pl.altkom.asc.lab.micronaut.poc.policy.shared.exceptions.BusinessException;

import javax.inject.Singleton;
import java.time.LocalDate;
import java.util.Optional;

@Singleton
@RequiredArgsConstructor
public class TerminatePolicyHandler implements CommandHandler<TerminatePolicyResult, TerminatePolicyCommand> {

    private final PolicyRepository policyRepository;
    private final EventPublisher eventPublisher;

    @Override
    public TerminatePolicyResult handle(TerminatePolicyCommand cmd) {
        Optional<Policy> policyOpt = policyRepository.findByNumber(cmd.getPolicyNumber());
        if (!policyOpt.isPresent())
            throw new BusinessException("POLICY NOT FOUND");

        Policy policy = policyOpt.get();
        policy.terminate(LocalDate.now());

        policyRepository.save(policy);

        eventPublisher.policyTerminatedEvent(policy.getNumber(), createEvent(policy));

        return TerminatePolicyResult.success(policy.getNumber());
    }

    private PolicyTerminatedEvent createEvent(Policy policy) {
        return new PolicyTerminatedEvent(new PolicyDto(
                policy.getNumber(),
                policy.versions().lastVersion().getVersionValidityPeriod().getFrom(),
                policy.versions().lastVersion().getVersionValidityPeriod().getTo(),
                policy.versions().lastVersion().getPolicyHolder().getFullName(),
                policy.versions().lastVersion().getProductCode(),
                policy.versions().lastVersion().getTotalPremiumAmount(),
                null
        ));
    }
}
