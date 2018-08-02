package pl.altkom.asc.lab.micronaut.poc.policy.commands

import pl.altkom.asc.lab.micronaut.poc.policy.commands.builders.PolicyBuilder
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyregister.RegisterPolicyCommand
import pl.altkom.asc.lab.micronaut.poc.policy.commands.RegisterPolicyHandler
import pl.altkom.asc.lab.micronaut.poc.policy.domain.Policy
import pl.altkom.asc.lab.micronaut.poc.policy.domain.PolicyRepository
import pl.altkom.asc.lab.micronaut.poc.policy.shared.exceptions.BusinessException
import spock.lang.Specification

class RegisterPolicyHandlerSpec extends Specification {
    PolicyBuilder policyBuilder = new PolicyBuilder()
    PolicyRepository policyRepository = Stub(PolicyRepository)

    RegisterPolicyHandler registerPolicyHandler = new RegisterPolicyHandler(policyRepository)

    void "can register new policy"() {
        given: "command to register new policyRef P1"
        def inMemoDb = new HashMap<String, Policy>()
        def cmd = new RegisterPolicyCommand(policyBuilder.buildDto("P1", 1))

        and: "policyRef does not exists"
        policyRepository.findByNumber(_ as String) >> Optional.empty()

        and:
        policyRepository.save(_ as Policy) >> { Policy p -> inMemoDb.put(p.number, p) }

        when: "handler is called"
        def registrationResult = registerPolicyHandler.handle(cmd)

        then:
        registrationResult != null
        inMemoDb["P1"] != null
        inMemoDb["P1"].number == "P1"
        inMemoDb["P1"].versions.size() == 1

    }

    void "new version is added to existing policy"() {
        given: "command to register version 3 of policyRef P1212121"
        def cmd = new RegisterPolicyCommand(policyBuilder.buildDto("P1212121", 3))

        and: "policyRef exists with version 1 and 2"
        Policy existingPolicy = policyBuilder.build()
        policyRepository.findByNumber("P1212121") >> Optional.of(existingPolicy)

        when: "handler is called"
        def registrationResult = registerPolicyHandler.handle(cmd)

        then: "third version is created"
        registrationResult != null
        existingPolicy.number == "P1212121"
        existingPolicy.versions.size() == 3

    }

    void "when version already exists exception is thrown"() {
        given: "command to register version 2 of policyRef P1212121"
        def cmd = new RegisterPolicyCommand(policyBuilder.buildDto("P1212121", 2))

        and: "policyRef exists with versions 1 and 2"
        Policy existingPolicy = policyBuilder.build()
        policyRepository.findByNumber("P1212121") >> Optional.of(existingPolicy)

        when: "handler is called"
        registerPolicyHandler.handle(cmd)

        then: "exception is thrown"
        BusinessException businessEx = thrown()
        businessEx.code == "POLVEREXISTS"
        businessEx.args[0] == "P1212121"
        businessEx.args[1] == 2

    }
}

