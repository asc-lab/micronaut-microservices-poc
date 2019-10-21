package pl.altkom.asc.lab.micronaut.poc.dashboard.infrastructure.adapters.elastic;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.filter.Filter;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.joda.time.DateTime;
import pl.altkom.asc.lab.micronaut.poc.dashboard.domain.SalesResult;
import pl.altkom.asc.lab.micronaut.poc.dashboard.domain.SalesTrendsQuery;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SalesTrendsQueryAdapter extends QueryAdapter<SalesTrendsQuery,SalesTrendsQuery.Result> {
    public SalesTrendsQueryAdapter(SalesTrendsQuery query) {
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

        DateHistogramAggregationBuilder histBuilder = AggregationBuilders
                .dateHistogram("sales")
                .field("from")
                .dateHistogramInterval(query.getAggregationUnit().toDateHistogramInterval())
                .subAggregation(AggregationBuilders.sum("total_premium").field("totalPremium"));
        aggBuilder.subAggregation(histBuilder);

        SearchSourceBuilder srcBuilder = new SearchSourceBuilder()
                .aggregation(aggBuilder)
                .size(0);
        searchRequest.source(srcBuilder);

        return searchRequest;
    }

    @Override
    SalesTrendsQuery.Result extractResult(SearchResponse searchResponse) {
        SalesTrendsQuery.Result.ResultBuilder result = SalesTrendsQuery.Result.builder();

        Filter filterAgg = searchResponse.getAggregations().get("agg_filter");
        Histogram agg = filterAgg.getAggregations().get("sales");
        for (Histogram.Bucket b : agg.getBuckets()){
            DateTime key = (DateTime)b.getKey();
            Sum sum = b.getAggregations().get("total_premium");
            result.periodSale(
                    new SalesTrendsQuery.PeriodSales(
                            LocalDate.of(key.getYear(),key.getMonthOfYear(),key.getDayOfMonth()),
                            b.getKeyAsString(),
                            SalesResult.of(b.getDocCount(), BigDecimal.valueOf(sum.getValue()).setScale(2, BigDecimal.ROUND_HALF_UP))
                    )
            );
        }

        return result.build();
    }
}
