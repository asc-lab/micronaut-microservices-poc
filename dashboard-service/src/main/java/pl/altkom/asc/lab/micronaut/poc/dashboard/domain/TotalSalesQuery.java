package pl.altkom.asc.lab.micronaut.poc.dashboard.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.Map;

@AllArgsConstructor
@Getter
@Builder
public class TotalSalesQuery {
    private String filterByProductCode;
    private LocalDateRange filterBySalesDate;

    @AllArgsConstructor
    @Builder
    @Getter
    public static class Result {
        private SalesResult total;
        @Singular("productTotal")
        private Map<String, SalesResult> perProductTotal;
    }
}
