package pl.altkom.asc.lab.micronaut.poc.payment.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue(value = "inpayment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InPayment extends AccountingEntry {
    public InPayment(PolicyAccount policyAccount, LocalDate creationDate, LocalDate effectiveDate, BigDecimal amount) {
        super(policyAccount,creationDate, effectiveDate, amount);
    }
    
    @Override
    public BigDecimal apply(BigDecimal state) {
        return state.add(getAmount());
    }
    
}
