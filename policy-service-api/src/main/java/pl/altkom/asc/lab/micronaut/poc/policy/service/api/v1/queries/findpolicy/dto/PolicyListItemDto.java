package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.findpolicy.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PolicyListItemDto {
    private String number;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String policyHolder;
    private BigDecimal totalPremium;
}
