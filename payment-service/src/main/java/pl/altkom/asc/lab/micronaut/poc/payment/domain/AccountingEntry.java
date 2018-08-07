package pl.altkom.asc.lab.micronaut.poc.payment.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Inheritance
@Table(name = "accounting_entry")
@DiscriminatorColumn(name = "enty_type")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class AccountingEntry {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "policy_account_id")
    private PolicyAccount policyAccount;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "effective_date")
    private LocalDate effectiveDate;

    @Column(name = "amount")
    private BigDecimal amount;

    public AccountingEntry(PolicyAccount policyAccount, LocalDate creationDate, LocalDate effectiveDate, BigDecimal amount) {
        this.policyAccount = policyAccount;
        this.creationDate = creationDate;
        this.effectiveDate = effectiveDate;
        this.amount = amount;
    }
    
    public abstract BigDecimal apply(BigDecimal state);

    boolean isEffectiveOn(LocalDate theDate) {
        return this.effectiveDate.isBefore(theDate) || this.effectiveDate.equals(theDate);
    }
}
