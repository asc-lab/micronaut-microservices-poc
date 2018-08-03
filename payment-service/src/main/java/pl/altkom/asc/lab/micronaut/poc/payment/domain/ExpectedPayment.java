package pl.altkom.asc.lab.micronaut.poc.payment.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue(value = "expected_payment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExpectedPayment extends AccountingEntry {

    public ExpectedPayment(PolicyAccount policyAccount, LocalDate creationDate, LocalDate effectiveDate, BigDecimal amount) {
        super(policyAccount,creationDate, effectiveDate, amount);
    }

    @Override
    public BigDecimal apply(BigDecimal state) {
        return state.subtract(getAmount());
    }
    
}
