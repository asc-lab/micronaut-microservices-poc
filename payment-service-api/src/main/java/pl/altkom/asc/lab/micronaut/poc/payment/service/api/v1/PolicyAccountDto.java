package pl.altkom.asc.lab.micronaut.poc.payment.service.api.v1;

import io.micronaut.core.annotation.Introspected;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Introspected
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PolicyAccountDto {
    private String policyAccountNumber;
    private String policyNumber;
    private Date created;
    private Date updated;
}
