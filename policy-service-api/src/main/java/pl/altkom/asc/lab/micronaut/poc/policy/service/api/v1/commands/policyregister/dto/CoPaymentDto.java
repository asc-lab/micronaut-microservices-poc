package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyregister.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoPaymentDto {
    private BigDecimal percent;
    private BigDecimal amount;
}
