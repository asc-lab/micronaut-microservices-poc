package pl.altkom.asc.lab.micronaut.poc.dashboard.infrastructure.adapters.elastic;

import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import pl.altkom.asc.lab.micronaut.poc.dashboard.domain.*;
import pl.altkom.asc.lab.micronaut.poc.dashboard.infrastructure.adapters.elastic.config.JsonConverter;

import javax.inject.Singleton;
import java.io.IOException;

@Singleton
@RequiredArgsConstructor
public class PolicyElasticRepository implements PolicyRepository {

    private final RestHighLevelClient esClient;
    private final JsonConverter jsonConverter;

    public void save(PolicyDocument policyDocument) {
        IndexRequest indexRequest = new IndexRequest("policy_stats")
                .type("policy_type")
                .id(policyDocument.getNumber())
                .setRefreshPolicy("true")
                .source(jsonConverter.stringifyObject(policyDocument), XContentType.JSON);

        try {
            esClient.index(indexRequest);
        } catch (IOException e) {
            throw new RuntimeException("Error while executing query", e);
        }
    }

    public PolicyDocument findByNumber(String number) {
        SearchRequest searchRequest = new SearchRequest("policy_stats")
                .types("policy_type");

        BoolQueryBuilder filterBuilder = QueryBuilders.boolQuery();

        filterBuilder.must(QueryBuilders.termQuery("number.keyword", number));

        SearchSourceBuilder srcBuilder = new SearchSourceBuilder()
                .query(filterBuilder)
                .size(10);

        searchRequest.source(srcBuilder);

        SearchResponse searchResponse = executeSearch(searchRequest);

        SearchHit[] hits = searchResponse.getHits().getHits();

        return hits.length > 0
                ? jsonConverter.objectFromString(hits[0].getSourceAsString(), PolicyDocument.class)
                : null;
    }

    public TotalSalesQuery.Result getTotalSales(TotalSalesQuery query) {
        TotalSalesQueryAdapter queryAdapter = QueryAdapter.of(query);
        SearchResponse searchResponse = executeSearch(queryAdapter.buildQuery());
        return queryAdapter.extractResult(searchResponse);
    }

    public SalesTrendsQuery.Result getSalesTrends(SalesTrendsQuery query) {
        SalesTrendsQueryAdapter queryAdapter = QueryAdapter.of(query);
        SearchResponse searchResponse = executeSearch(queryAdapter.buildQuery());
        return queryAdapter.extractResult(searchResponse);
    }

    public AgentSalesQuery.Result getAgentSales(AgentSalesQuery query) {
        AgentSalesQueryAdapter queryAdapter = QueryAdapter.of(query);
        SearchResponse searchResponse = executeSearch(queryAdapter.buildQuery());
        return queryAdapter.extractResult(searchResponse);
    }

    private SearchResponse executeSearch(SearchRequest request) {
        try {
            return esClient.search(request);
        } catch (IOException e) {
            throw new RuntimeException("Failed to execute search", e);
        }
    }
}
