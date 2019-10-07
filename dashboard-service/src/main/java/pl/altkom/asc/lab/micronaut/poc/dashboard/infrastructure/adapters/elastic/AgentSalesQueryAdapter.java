package pl.altkom.asc.lab.micronaut.poc.dashboard.infrastructure.adapters.elastic;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.filter.Filter;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import pl.altkom.asc.lab.micronaut.poc.dashboard.domain.AgentSalesQuery;
import pl.altkom.asc.lab.micronaut.poc.dashboard.domain.SalesResult;

import java.math.BigDecimal;

public class AgentSalesQueryAdapter extends QueryAdapter<AgentSalesQuery,AgentSalesQuery.Result> {
    public AgentSalesQueryAdapter(AgentSalesQuery query) {
        super(query);
    }

    @Override
    SearchRequest buildQuery() {
        SearchRequest searchRequest = new SearchRequest("policy_stats")
                .types("policy_type");

        BoolQueryBuilder filterBuilder = QueryBuilders.boolQuery();
        if (query.getFilterByAgentLogin()!=null) {
            filterBuilder.must(QueryBuilders.termQuery("agentLogin.keyword", query.getFilterByAgentLogin()));
        }
        if (query.getFilterByProductCode()!=null) {
            filterBuilder.must(QueryBuilders.termQuery("productCode.keyword", query.getFilterByProductCode()));
        }
        if (query.getFilterBySalesDate()!=null){
            RangeQueryBuilder datesRange = QueryBuilders
                    .rangeQuery("from")
                    .gte(query.getFilterBySalesDate().getFrom().toString())
                    .lt(query.getFilterBySalesDate().getTo().toString());
            filterBuilder.must(datesRange);
        }
        AggregationBuilder aggBuilder = AggregationBuilders.filter("agg_filter",filterBuilder);

        TermsAggregationBuilder sumAggBuilder = AggregationBuilders
                .terms("count_by_agent")
                .field("agentLogin.keyword")
                .subAggregation(AggregationBuilders.sum("total_premium").field("totalPremium"));
        aggBuilder.subAggregation(sumAggBuilder);

        SearchSourceBuilder srcBuilder = new SearchSourceBuilder()
                .query(QueryBuilders.matchAllQuery())
                .aggregation(aggBuilder)
                .size(0);
        searchRequest.source(srcBuilder);

        return searchRequest;
    }

    @Override
    AgentSalesQuery.Result extractResult(SearchResponse searchResponse) {
        AgentSalesQuery.Result.ResultBuilder result = AgentSalesQuery.Result.builder();
        Filter filterAgg = searchResponse.getAggregations().get("agg_filter");
        Terms agents = filterAgg.getAggregations().get("count_by_agent");
        for (Terms.Bucket b : agents.getBuckets()){
            Sum sum = b.getAggregations().get("total_premium");
            result.agentTotal(b.getKeyAsString(), SalesResult.of(b.getDocCount(),BigDecimal.valueOf(sum.getValue())));
        }

        return result.build();
    }
}
