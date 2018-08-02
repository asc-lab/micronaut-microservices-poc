package pl.altkom.asc.lab.micronaut.poc.policy.commands.builders

import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyregister.dto.CoPaymentDto
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyregister.dto.LimitDto
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyregister.dto.PersonDto
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyregister.dto.PolicyVersionDto
import pl.altkom.asc.lab.micronaut.poc.policy.domain.Person
import pl.altkom.asc.lab.micronaut.poc.policy.domain.Policy
import pl.altkom.asc.lab.micronaut.poc.policy.domain.PolicyVersion
import pl.altkom.asc.lab.micronaut.poc.policy.domain.vo.DateRange

import java.time.LocalDate

class PolicyBuilder {

    static Policy build() {
        Policy policy = new Policy("P1212121")

        PolicyVersion versionOne = policy.versions().add(
                1L,
                "Pakiet Gold",
                new Person("Jan", "Nowak", "111111116"),
                "2738123834783247723",
                DateRange.between(LocalDate.of(2018, 1, 1), LocalDate.of(2018, 12, 31)),
                DateRange.between(LocalDate.of(2018, 1, 1), LocalDate.of(9999, 12, 31)))

        PolicyVersion versionTwo = policy.versions().add(
                2L,
                "Pakiet Gold",
                new Person("Jan", "Nowak", "111111116"),
                "2738123834783247723",
                DateRange.between(LocalDate.of(2018, 1, 1), LocalDate.of(2018, 12, 31)),
                DateRange.between(LocalDate.of(2018, 7, 1), LocalDate.of(9999, 12, 31)))

        return policy
    }

    PolicyVersionDto buildDto(String number, Long version) {
        PolicyVersionDto pv = PolicyVersionDto.builder()
                .policyNumber(number)
                .productCode("ABO_GOLD")
                .policyHolder(new PersonDto("Jan", "Bak", "11111111116"))
                .accountNumber("901291092012910")
                .policyValidFrom(LocalDate.of(2018, 1, 1))
                .policyValidTo(LocalDate.of(2018, 12, 31))
                .versionNumber(version)
                .versionValidFrom(LocalDate.of(2018, 1, 1))
                .versionValidTo(LocalDate.of(2018, 12, 31))
                .covers([
                CoverDto.builder()
                        .code("C1")
                        .services([
                        ServiceDto.builder()
                                .code("S1")
                                .coPayment(new CoPaymentDto(10, null))
                                .limit(new LimitDto("POLICY_YEAR", 10, null))
                                .build()
                ]).build()
        ]).build()

        return pv
    }

    PolicyVersionDto buildDto() {
        return buildDto("P1", 1)
    }
}
