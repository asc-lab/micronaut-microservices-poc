package pl.altkom.asc.lab.micronaut.poc.dashboard.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class SalesTrendsQuery {
    private String filterByProductCode;
    private LocalDateRange filterBySalesDate;
    private TimeAggregationUnit aggregationUnit;

    @AllArgsConstructor
    @Builder
    @Getter
    public static class Result {
        @Singular
        private List<PeriodSales> periodSales;
    }

    @AllArgsConstructor
    @Builder
    @Getter
    public static class PeriodSales {
        private LocalDate periodDate;
        private String period;
        private SalesResult sales;
    }
}
