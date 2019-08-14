package pl.altkom.asc.lab.micronaut.poc.payment.service.api.v1;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PolicyAccountBalanceDto {
    private String policyAccountNumber;
    private String policyNumber;
    private BigDecimal balance;
    private Date created;
    private Date updated;
}
