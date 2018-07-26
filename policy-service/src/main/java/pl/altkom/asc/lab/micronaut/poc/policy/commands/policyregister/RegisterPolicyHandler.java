package pl.altkom.asc.lab.micronaut.poc.policy.commands.policyregister;

import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.Policy;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.PolicyFactory;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.PolicyRegisteredEvent;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.PolicyRepository;
import pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.bus.CommandHandler;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
@RequiredArgsConstructor
public class RegisterPolicyHandler implements CommandHandler<RegisterPolicyResult, RegisterPolicyCommand> {

    private final PolicyRepository policyRepository;
    private final PolicyFactory policyFactory = new PolicyFactory();
    private final RegisterPolicyClient policyClient;

    @Override
    public RegisterPolicyResult handle(RegisterPolicyCommand cmd) {
        Optional<Policy> policyOpt = policyRepository.findByNumber(cmd.getPolicyVersion().getPolicyNumber());

        Policy policy = policyOpt.isPresent()
                ? addNewVersionToExistingPolicy(cmd, policyOpt.get())
                : createNewPolicyWithFirstVersion(cmd);

        policyRepository.save(policy);
        policyClient.policyRegisteredEvent(policy.getNumber(), new PolicyRegisteredEvent(policy));

        return RegisterPolicyResult.success(policy);
    }

    private Policy createNewPolicyWithFirstVersion(RegisterPolicyCommand registerPolicyCommand) {
        return policyFactory.create(registerPolicyCommand.getPolicyVersion());
    }

    private Policy addNewVersionToExistingPolicy(RegisterPolicyCommand registerPolicyCommand, Policy policy) {
        policyFactory.addVersion(registerPolicyCommand.getPolicyVersion(), policy);
        return policy;
    }
}
