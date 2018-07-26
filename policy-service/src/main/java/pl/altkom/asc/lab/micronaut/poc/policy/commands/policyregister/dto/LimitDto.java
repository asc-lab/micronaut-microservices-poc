package pl.altkom.asc.lab.micronaut.poc.policy.commands.policyregister.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LimitDto {
    private String periodTypeCode;
    private BigDecimal maxQuantity;
    private BigDecimal maxAmount;
}
