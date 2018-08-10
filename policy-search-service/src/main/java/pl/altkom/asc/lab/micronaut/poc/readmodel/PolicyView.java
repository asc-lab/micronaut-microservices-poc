package pl.altkom.asc.lab.micronaut.poc.readmodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
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

    public PolicyView(String number) {
        this.number = number;
    }
}
