package pl.altkom.asc.lab.micronaut.poc.dashboard.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.Map;

@AllArgsConstructor
@Getter
@Builder
public class AgentSalesQuery {
    private String filterByAgentLogin;
    private String filterByProductCode;
    private LocalDateRange filterBySalesDate;

    @AllArgsConstructor
    @Builder
    @Getter
    public static class Result {
        @Singular("agentTotal")
        private Map<String, SalesResult> perAgentTotal;
    }
}
