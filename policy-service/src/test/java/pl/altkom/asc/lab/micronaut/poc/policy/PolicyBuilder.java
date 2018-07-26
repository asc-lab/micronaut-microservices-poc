package pl.altkom.asc.lab.micronaut.poc.policy;

import pl.altkom.asc.lab.micronaut.poc.policy.commands.policyregister.dto.*;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.Person;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.Policy;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.PolicyVersion;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.vo.DateRange;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class PolicyBuilder {

    static Policy build() {
        return new Policy(1L,
                "P1212121",
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
                                null
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
                                null
                        )
                )));
    }

    static PolicyVersionDto buildDto(String number, Long version) {
        return PolicyVersionDto.builder()
                .policyNumber(number)
                .productCode("ABO_GOLD")
                .policyHolder(new PersonDto("Jan", "Bak", "11111111116"))
                .accountNumber("901291092012910")
                .policyValidFrom(LocalDate.of(2018, 1, 1))
                .policyValidTo(LocalDate.of(2018, 12, 31))
                .versionNumber(version)
                .versionValidFrom(LocalDate.of(2018, 1, 1))
                .versionValidTo(LocalDate.of(2018, 12, 31))
                .covers(Collections.singletonList(
                        CoverDto.builder()
                                .code("C1")
                                .services(Collections.singletonList(
                                        ServiceDto.builder()
                                                .code("S1")
                                                .coPayment(new CoPaymentDto(BigDecimal.valueOf(10), null))
                                                .limit(new LimitDto("POLICY_YEAR", BigDecimal.valueOf(10), null))
                                                .build()
                                )).build()
                ))
                .build();
    }

    static PolicyVersionDto buildDto() {
        return buildDto("P1", 1L);
    }
}
