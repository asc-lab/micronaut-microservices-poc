package pl.altkom.asc.lab.micronaut.poc.dashboard.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PolicyDocument {
    private String number;
    private LocalDate from;
    private LocalDate to;
    private String policyHolder;
    private String productCode;
    private BigDecimal totalPremium;
    private String agentLogin;
}
