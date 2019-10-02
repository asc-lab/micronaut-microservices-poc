package pl.altkom.asc.lab.micronaut.poc.dashboard.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class SalesResult {
    private Long policiesCount;
    private BigDecimal premiumAmount;

    public static SalesResult of(Long count,BigDecimal total) {
        return new SalesResult(count,total);
    }
}
