package pl.altkom.asc.lab.micronaut.poc.gateway;


import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import pl.altkom.asc.lab.micronaut.poc.dashboard.service.api.v1.queries.getagentssalesquery.GetAgentsSalesQuery;
import pl.altkom.asc.lab.micronaut.poc.dashboard.service.api.v1.queries.getagentssalesquery.GetAgentsSalesQueryResult;
import pl.altkom.asc.lab.micronaut.poc.dashboard.service.api.v1.queries.getsalestrendsquery.GetSalesTrendsQuery;
import pl.altkom.asc.lab.micronaut.poc.dashboard.service.api.v1.queries.getsalestrendsquery.GetSalesTrendsQueryResult;
import pl.altkom.asc.lab.micronaut.poc.dashboard.service.api.v1.queries.gettotalsalesquery.GetTotalSalesQuery;
import pl.altkom.asc.lab.micronaut.poc.dashboard.service.api.v1.queries.gettotalsalesquery.GetTotalSalesQueryResult;
import pl.altkom.asc.lab.micronaut.poc.gateway.client.v1.DashboardGatewayClient;

import javax.inject.Inject;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/api/dashboard")
public class DashboardGatewayController {

    @Inject
    private DashboardGatewayClient client;

    @Post("/totalsales")
    GetTotalSalesQueryResult queryTotalSales(@Body GetTotalSalesQuery query){
        return client.queryTotalSales(query);
    }

    @Post("/trends")
    GetSalesTrendsQueryResult querySalesTrends(@Body GetSalesTrendsQuery query){
        return client.querySalesTrends(query);
    }

    @Post("/agentssales")
    GetAgentsSalesQueryResult queryAgentsSales(@Body GetAgentsSalesQuery query){
        return client.queryAgentsSales(query);
    }
}
