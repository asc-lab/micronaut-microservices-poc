package pl.altkom.asc.lab.micronaut.poc.policy;

import pl.altkom.asc.lab.micronaut.poc.policy.domain.AgentRef;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.Person;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.Policy;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.PolicyVersion;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.vo.DateRange;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

class PolicyBuilder {

    static Policy build() {
        return new Policy(1L,
                "P1212121",
                AgentRef.of("admin"),
                new HashSet<>(Arrays.asList(
                        new PolicyVersion(
                                1L,
                                null,
                                1L,
                                "Pakiet Gold",
                                new Person("Jan", "Nowak", "111111116"),
                                "2738123834783247723",
                                DateRange.between(LocalDate.of(2018, 1, 1), LocalDate.of(2018, 12, 31)),
                                DateRange.between(LocalDate.of(2018, 1, 1), LocalDate.of(9999, 12, 31)),
                                null,
                                new BigDecimal("199")
                        ),
                        new PolicyVersion(
                                2L,
                                null,
                                2L,
                                "Pakiet Gold",
                                new Person("Jan", "Nowak", "111111116"),
                                "2738123834783247723",
                                DateRange.between(LocalDate.of(2018, 1, 1), LocalDate.of(2018, 12, 31)),
                                DateRange.between(LocalDate.of(2018, 1, 1), LocalDate.of(9999, 12, 31)),
                                null,
                                new BigDecimal("199")
                        )
                )));
    }
}
