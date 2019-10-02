package pl.altkom.asc.lab.micronaut.poc.dashboard.infrastructure.adapters.elastic;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import pl.altkom.asc.lab.micronaut.poc.dashboard.domain.AgentSalesQuery;
import pl.altkom.asc.lab.micronaut.poc.dashboard.domain.SalesTrendsQuery;
import pl.altkom.asc.lab.micronaut.poc.dashboard.domain.TotalSalesQuery;

abstract class QueryAdapter<TQuery, TQueryResult> {
    protected TQuery query;

    QueryAdapter(TQuery query) {
        this.query = query;
    }

    abstract SearchRequest buildQuery();
    abstract TQueryResult extractResult(SearchResponse searchResponse);

    static TotalSalesQueryAdapter of(TotalSalesQuery query) {
        return new TotalSalesQueryAdapter(query);
    }

    static SalesTrendsQueryAdapter of(SalesTrendsQuery query) {
        return new SalesTrendsQueryAdapter(query);
    }

    static AgentSalesQueryAdapter of(AgentSalesQuery query) {
        return new AgentSalesQueryAdapter(query);
    }
}
