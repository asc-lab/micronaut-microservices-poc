package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyregister.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceDto {
    private String code;
    private CoPaymentDto coPayment;
    private LimitDto limit;
}
