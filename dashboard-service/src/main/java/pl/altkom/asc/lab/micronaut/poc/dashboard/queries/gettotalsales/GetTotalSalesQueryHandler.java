package pl.altkom.asc.lab.micronaut.poc.dashboard.queries.gettotalsales;

import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.command.bus.QueryHandler;
import pl.altkom.asc.lab.micronaut.poc.dashboard.domain.LocalDateRange;
import pl.altkom.asc.lab.micronaut.poc.dashboard.domain.PolicyRepository;
import pl.altkom.asc.lab.micronaut.poc.dashboard.domain.TotalSalesQuery;
import pl.altkom.asc.lab.micronaut.poc.dashboard.service.api.v1.queries.gettotalsalesquery.GetTotalSalesQuery;
import pl.altkom.asc.lab.micronaut.poc.dashboard.service.api.v1.queries.gettotalsalesquery.GetTotalSalesQueryResult;

import javax.inject.Singleton;


@Singleton
@RequiredArgsConstructor
public class GetTotalSalesQueryHandler implements QueryHandler<GetTotalSalesQueryResult, GetTotalSalesQuery> {

    private final PolicyRepository policyRepository;

    @Override
    public GetTotalSalesQueryResult handle(GetTotalSalesQuery query) {
        TotalSalesQuery.Result totalSales = policyRepository.getTotalSales(TotalSalesQuery.builder()
                .filterByProductCode(query.getProductCode())
                .filterBySalesDate(LocalDateRange.between(query.getSaleDateFrom(), query.getSaleDateTo()))
                .build());
        return GetTotalSalesQueryResultAssembler.assemble(totalSales);
    }
}
