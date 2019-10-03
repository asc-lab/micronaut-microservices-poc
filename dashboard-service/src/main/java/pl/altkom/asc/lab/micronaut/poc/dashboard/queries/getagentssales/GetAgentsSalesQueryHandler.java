package pl.altkom.asc.lab.micronaut.poc.dashboard.queries.getagentssales;

import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.command.bus.QueryHandler;
import pl.altkom.asc.lab.micronaut.poc.dashboard.domain.AgentSalesQuery;
import pl.altkom.asc.lab.micronaut.poc.dashboard.domain.LocalDateRange;
import pl.altkom.asc.lab.micronaut.poc.dashboard.domain.PolicyRepository;
import pl.altkom.asc.lab.micronaut.poc.dashboard.service.api.v1.queries.getagentssalesquery.GetAgentsSalesQuery;
import pl.altkom.asc.lab.micronaut.poc.dashboard.service.api.v1.queries.getagentssalesquery.GetAgentsSalesQueryResult;

import javax.inject.Singleton;

@Singleton
@RequiredArgsConstructor
public class GetAgentsSalesQueryHandler implements QueryHandler<GetAgentsSalesQueryResult, GetAgentsSalesQuery> {

    private final PolicyRepository policyRepository;

    @Override
    public GetAgentsSalesQueryResult handle(GetAgentsSalesQuery query) {
        AgentSalesQuery.Result agentsSales = policyRepository.getAgentSales(AgentSalesQuery.builder()
                .filterByAgentLogin(query.getAgentLogin())
                .filterByProductCode(query.getProductCode())
                .filterBySalesDate(LocalDateRange.between(query.getSaleDateFrom(),query.getSaleDateTo()))
                .build());
        return GetAgentsSalesQueryResultAssembler.assemble(agentsSales);
    }
}
