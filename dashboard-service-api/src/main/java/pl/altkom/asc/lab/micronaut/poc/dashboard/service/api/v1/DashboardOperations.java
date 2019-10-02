package pl.altkom.asc.lab.micronaut.poc.dashboard.service.api.v1;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import pl.altkom.asc.lab.micronaut.poc.dashboard.service.api.v1.queries.gettotalsalesquery.GetTotalSalesQuery;
import pl.altkom.asc.lab.micronaut.poc.dashboard.service.api.v1.queries.gettotalsalesquery.GetTotalSalesQueryResult;

public interface DashboardOperations {

    @Post
    GetTotalSalesQueryResult queryTotalSales(@Body GetTotalSalesQuery query);
}
