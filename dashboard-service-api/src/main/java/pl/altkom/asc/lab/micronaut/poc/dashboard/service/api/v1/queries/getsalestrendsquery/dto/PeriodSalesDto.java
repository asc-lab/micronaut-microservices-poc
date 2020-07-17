package pl.altkom.asc.lab.micronaut.poc.dashboard.service.api.v1.queries.getsalestrendsquery.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Introspected
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PeriodSalesDto {
    private LocalDate periodDate;
    private String period;
    private Long policiesCount;
    private BigDecimal premiumAmount;
}
