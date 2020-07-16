package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Introspected
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PolicyDto {
    private String number;
    private LocalDate from;
    private LocalDate to;
    private String policyHolder;
    private String productCode;
    private BigDecimal totalPremium;
    private String agentLogin;
}
