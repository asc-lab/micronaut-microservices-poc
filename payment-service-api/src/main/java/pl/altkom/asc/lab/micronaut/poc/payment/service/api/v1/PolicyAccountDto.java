package pl.altkom.asc.lab.micronaut.poc.payment.service.api.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PolicyAccountDto {
    private String policyAccountNumber;
    private String policyNumber;
}
