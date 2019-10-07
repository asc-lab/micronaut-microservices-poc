package pl.altkom.asc.lab.micronaut.poc.dashboard.queries.gettotalsales;

import pl.altkom.asc.lab.micronaut.poc.dashboard.domain.TotalSalesQuery;
import pl.altkom.asc.lab.micronaut.poc.dashboard.service.api.v1.queries.gettotalsalesquery.GetTotalSalesQueryResult;
import pl.altkom.asc.lab.micronaut.poc.dashboard.service.api.v1.queries.gettotalsalesquery.dto.SalesDto;

import java.util.HashMap;

class GetTotalSalesQueryResultAssembler {
    static GetTotalSalesQueryResult assemble(TotalSalesQuery.Result queryResult) {
        GetTotalSalesQueryResult result = new GetTotalSalesQueryResult(
            new SalesDto(queryResult.getTotal().getPoliciesCount(), queryResult.getTotal().getPremiumAmount()),
            new HashMap<>()
        );
        queryResult.getPerProductTotal().forEach((k,v) ->
            result.getPerProductTotal().put(k, new SalesDto(v.getPoliciesCount(),v.getPremiumAmount()))
        );
        return result;
    }
}
