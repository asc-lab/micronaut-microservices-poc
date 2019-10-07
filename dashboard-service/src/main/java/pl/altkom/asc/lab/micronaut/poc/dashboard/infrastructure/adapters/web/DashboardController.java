package pl.altkom.asc.lab.micronaut.poc.dashboard.infrastructure.adapters.web;


import io.micronaut.http.annotation.Controller;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.command.bus.CommandBus;
import pl.altkom.asc.lab.micronaut.poc.dashboard.service.api.v1.DashboardOperations;
import pl.altkom.asc.lab.micronaut.poc.dashboard.service.api.v1.queries.getagentssalesquery.GetAgentsSalesQuery;
import pl.altkom.asc.lab.micronaut.poc.dashboard.service.api.v1.queries.getagentssalesquery.GetAgentsSalesQueryResult;
import pl.altkom.asc.lab.micronaut.poc.dashboard.service.api.v1.queries.getsalestrendsquery.GetSalesTrendsQuery;
import pl.altkom.asc.lab.micronaut.poc.dashboard.service.api.v1.queries.getsalestrendsquery.GetSalesTrendsQueryResult;
import pl.altkom.asc.lab.micronaut.poc.dashboard.service.api.v1.queries.gettotalsalesquery.GetTotalSalesQuery;
import pl.altkom.asc.lab.micronaut.poc.dashboard.service.api.v1.queries.gettotalsalesquery.GetTotalSalesQueryResult;


@RequiredArgsConstructor
@Validated
@Controller("/dashboard")
public class DashboardController implements DashboardOperations {

    private final CommandBus bus;

    @Override
    public GetTotalSalesQueryResult queryTotalSales(GetTotalSalesQuery query) {
        return bus.executeQuery(query);
    }

    @Override
    public GetSalesTrendsQueryResult querySalesTrends(GetSalesTrendsQuery query) {
        return bus.executeQuery(query);
    }

    @Override
    public GetAgentsSalesQueryResult queryAgentsSales(GetAgentsSalesQuery query) {
        return bus.executeQuery(query);
    }
}
