package pl.altkom.asc.lab.micronaut.poc.policy.search.queries.findpolicy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import pl.altkom.asc.lab.micronaut.poc.policy.search.readmodel.PolicyView;
import pl.altkom.asc.lab.micronaut.poc.policy.search.service.api.v1.queries.findpolicy.FindPolicyQueryResult;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PolicyQueryResultAssemblerTest {

    @Test
    public void shouldSortResultsByDescendingStartDate() {
        PolicyQueryResultAssembler assembler = new PolicyQueryResultAssembler();
        List<PolicyView> policies = new ArrayList<>(Arrays.asList(
                new PolicyView("1", LocalDate.of(2015,11,1), LocalDate.of(2016,10,30), "Tom Hanks"),
                new PolicyView("2", LocalDate.of(2015,11,3), LocalDate.of(2016,10,30), "Alanis Morissette"),
                new PolicyView("3", LocalDate.of(2015,11,2), LocalDate.of(2016,10,30), "Andy Warhol")
        ));

        FindPolicyQueryResult result = assembler.constructResult(policies);

        Assertions.assertTrue(result.getPolicies().get(0).getNumber().equalsIgnoreCase("2"));
        Assertions.assertTrue(result.getPolicies().get(1).getNumber().equalsIgnoreCase("3"));
        Assertions.assertTrue(result.getPolicies().get(2).getNumber().equalsIgnoreCase("1"));
    }


    @Test
    public void shouldHandleNullStartDatesAndPlaceThemAtTheEnd() {
        PolicyQueryResultAssembler assembler = new PolicyQueryResultAssembler();
        List<PolicyView> policies = new ArrayList<>(Arrays.asList(
                new PolicyView("1", LocalDate.of(2015,11,1), LocalDate.of(2016,10,30), "Tom Hanks"),
                new PolicyView("2", null, null, "Alanis Morissette"),
                new PolicyView("3", LocalDate.of(2015,11,2), LocalDate.of(2016,10,30), "Andy Warhol")
        ));

        FindPolicyQueryResult result = assembler.constructResult(policies);

        Assertions.assertTrue(result.getPolicies().get(2).getNumber().equalsIgnoreCase("2"));

    }
}
