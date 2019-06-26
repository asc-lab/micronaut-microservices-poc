package pl.altkom.asc.lab.micronaut.poc.policy.search.queries.findpolicy

import java.time.LocalDate
import pl.altkom.asc.lab.micronaut.poc.policy.search.readmodel.PolicyView
import pl.altkom.asc.lab.micronaut.poc.policy.search.service.api.v1.queries.findpolicy.FindPolicyQueryResult
import spock.lang.Specification

class PolicyQueryResultAssemblerSpec extends Specification {

    def 'should sort results by descending start date'() {
        given:
            PolicyQueryResultAssembler assembler = new PolicyQueryResultAssembler()
            List<PolicyView> policies = new ArrayList<>(Arrays.asList(
                    new PolicyView('1', LocalDate.of(2015,11,1), LocalDate.of(2016,10,30), 'Tom Hanks'),
                    new PolicyView('2', LocalDate.of(2015,11,3), LocalDate.of(2016,10,30), 'Alanis Morissette'),
                    new PolicyView('3', LocalDate.of(2015,11,2), LocalDate.of(2016,10,30), 'Andy Warhol')
            ))

        when:
            FindPolicyQueryResult result = assembler.constructResult(policies)

        then:
            result.policies.get(0).number.equalsIgnoreCase("2")
            result.policies.get(1).number.equalsIgnoreCase("3")
            result.policies.get(2).number.equalsIgnoreCase("1")
    }

    def 'should handle null start dates and place them at the end'() {
        given:
            PolicyQueryResultAssembler assembler = new PolicyQueryResultAssembler()
            List<PolicyView> policies = new ArrayList<>(Arrays.asList(
                    new PolicyView('1', LocalDate.of(2015,11,1), LocalDate.of(2016,10,30), 'Tom Hanks'),
                    new PolicyView('2', null, null, 'Alanis Morissette'),
                    new PolicyView('3', LocalDate.of(2015,11,2), LocalDate.of(2016,10,30), 'Andy Warhol')
            ))

        when:
            FindPolicyQueryResult result = assembler.constructResult(policies)

        then:
           result.policies.get(2).number.equalsIgnoreCase("2")
    }
}