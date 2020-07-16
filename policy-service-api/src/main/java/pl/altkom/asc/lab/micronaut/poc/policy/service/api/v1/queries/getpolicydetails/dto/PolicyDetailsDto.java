package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.getpolicydetails.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Introspected
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PolicyDetailsDto {
    private String number;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String policyHolder;
    private BigDecimal totalPremium;
    private String productCode;
    private String accountNumber;

    private Set<String> covers;
}
