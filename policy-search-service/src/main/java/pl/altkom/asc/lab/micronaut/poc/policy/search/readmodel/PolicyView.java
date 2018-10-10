package pl.altkom.asc.lab.micronaut.poc.policy.search.readmodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PolicyView {
    private String number;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String policyHolder;

    public PolicyView(String number) {
        this.number = number;
    }
}
