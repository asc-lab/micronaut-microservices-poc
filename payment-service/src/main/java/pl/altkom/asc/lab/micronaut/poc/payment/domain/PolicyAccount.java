package pl.altkom.asc.lab.micronaut.poc.payment.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "policy_account")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PolicyAccount {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "policy_number")
    private String policyNumber;
    @Column(name = "policy_account_number")
    private String policyAccountNumber;
    @OneToMany(mappedBy = "policyAccount", cascade = CascadeType.ALL)
    private List<AccountingEntry> entries;

    public PolicyAccount(String policyNumber, String policyAccountNumber) {
        this.policyNumber = policyNumber;
        this.policyAccountNumber = policyAccountNumber;
        this.entries = new ArrayList<>();
    }

    public void expectedPayment(BigDecimal amount, LocalDate dueDate) {
        entries.add(new ExpectedPayment(this,LocalDate.now(), dueDate, amount));
    }
    
    void inPayment(BigDecimal amount, LocalDate incomeDate) {
        entries.add(new InPayment(this,LocalDate.now(), incomeDate, amount));
    }
    
    void outPayment(BigDecimal amount, LocalDate paymentReleaseDate) {
        entries.add(new OutPayment(this,LocalDate.now(), paymentReleaseDate, amount));
    }
    
    public BigDecimal balanceAt(LocalDate effectiveDate) {
        List<AccountingEntry> effectiveEntries = entries.stream()
                .sorted((e1,e2) -> e1.getCreationDate().compareTo(e2.getCreationDate()))
                .filter(e -> e.isEffectiveOn(effectiveDate))
                .collect(Collectors.toList());
        
        BigDecimal balance = BigDecimal.ZERO;
        for (AccountingEntry entry : effectiveEntries) {
            balance = entry.apply(balance);
        }
                
        return balance;
    }
}
