package pl.altkom.asc.lab.micronaut.poc.policy.commands;

import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.policy.client.kafka.KafkaPolicyClient;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyclose.ClosePolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyclose.ClosePolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.Policy;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.PolicyClosedEvent;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.PolicyRepository;
import pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.bus.CommandHandler;
import pl.altkom.asc.lab.micronaut.poc.policy.shared.exceptions.BusinessException;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
@RequiredArgsConstructor
public class ClosePolicyHandler implements CommandHandler<ClosePolicyResult, ClosePolicyCommand> {

    private final PolicyRepository policyRepository;
    private final KafkaPolicyClient policyClient;

    @Override
    public ClosePolicyResult handle(ClosePolicyCommand cmd) {
        Optional<Policy> policyOpt = policyRepository.findByNumber(cmd.getPolicyNumber());
        if(!policyOpt.isPresent())
            throw new BusinessException("POLICY NOT FOUND");

        Policy policy = policyOpt.get();
        policy.close();

        policyRepository.save(policy);
        policyClient.policyClosedEvent(policy.getNumber(), new PolicyClosedEvent(policy));

        return ClosePolicyResult.success(policy.getNumber());
    }
}
