package pl.altkom.asc.lab.micronaut.poc.policy.readmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PolicyView {
    @Id
    @GeneratedValue
    private Long id;
    private String number;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String policyHolder;
    private BigDecimal totalPremium;

    public PolicyView(String number) {
        this.number = number;
    }
}
