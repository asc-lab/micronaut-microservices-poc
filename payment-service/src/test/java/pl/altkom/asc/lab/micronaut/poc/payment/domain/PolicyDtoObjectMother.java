package pl.altkom.asc.lab.micronaut.poc.payment.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.dto.PolicyDto;

public class PolicyDtoObjectMother {
  
  public static PolicyDto samplePolicyWithNumber(String number) {
    return PolicyDto.builder()
        .number(number)
        .from(LocalDate.now())
        .to(LocalDate.now().plusYears(1).minusDays(1))
        .policyHolder("Mark E Smith")
        .productCode("HFI")
        .totalPremium(BigDecimal.valueOf(10_000L))
        .agentLogin("jimmy solid")
        .build();
  }
  
}
