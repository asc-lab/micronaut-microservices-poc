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
import pl.altkom.asc.lab.micronaut.poc.dashboard.domain.SalesResult;
import pl.altkom.asc.lab.micronaut.poc.dashboard.domain.TotalSalesQuery;

import java.math.BigDecimal;

class TotalSalesQueryAdapter extends QueryAdapter<TotalSalesQuery, TotalSalesQuery.Result> {

    public TotalSalesQueryAdapter(TotalSalesQuery query) {
        super(query);
    }

    @Override
    SearchRequest buildQuery() {
        SearchRequest searchRequest = new SearchRequest("policy_stats")
                .types("policy_type");

        BoolQueryBuilder filterBuilder = QueryBuilders.boolQuery();
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
                .terms("count_by_product")
                .field("productCode.keyword")
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
    TotalSalesQuery.Result extractResult(SearchResponse searchResponse) {
        TotalSalesQuery.Result.ResultBuilder result = TotalSalesQuery.Result.builder();
        long count = 0;
        BigDecimal amount = BigDecimal.ZERO;
        Filter filterAgg = searchResponse.getAggregations().get("agg_filter");
        Terms products = filterAgg.getAggregations().get("count_by_product");
        for (Terms.Bucket b : products.getBuckets()){
            count += b.getDocCount();
            Sum sum = b.getAggregations().get("total_premium");
            amount = amount.add(BigDecimal.valueOf(sum.getValue()).setScale(2,BigDecimal.ROUND_HALF_UP));
            result.productTotal(b.getKeyAsString(), SalesResult.of(b.getDocCount(),BigDecimal.valueOf(sum.getValue())));
        }
        result.total(SalesResult.of(count,amount));

        return result.build();
    }

}
