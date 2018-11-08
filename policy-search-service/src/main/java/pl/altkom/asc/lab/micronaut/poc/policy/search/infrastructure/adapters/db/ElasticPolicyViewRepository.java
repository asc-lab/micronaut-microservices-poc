package pl.altkom.asc.lab.micronaut.poc.policy.search.infrastructure.adapters.db;

import io.reactivex.Maybe;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import pl.altkom.asc.lab.micronaut.poc.policy.search.readmodel.PolicyView;
import pl.altkom.asc.lab.micronaut.poc.policy.search.readmodel.PolicyViewRepository;
import pl.altkom.asc.lab.micronaut.poc.policy.search.service.api.v1.queries.findpolicy.FindPolicyQuery;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class ElasticPolicyViewRepository implements PolicyViewRepository {

    private static final String INDEX_NAME = "policy-views";

    private final ElasticClientAdapter elasticClientAdapter;
    private final JsonConverter jsonConverter;
    
    @Override
    public void save(PolicyView policy) {
        IndexRequest indexRequest = new IndexRequest(INDEX_NAME,"policyview", policy.getNumber());
        indexRequest.source(jsonConverter.stringifyObject(policy), XContentType.JSON);
        elasticClientAdapter.index(indexRequest).blockingGet();
    }
    
    @Override
    public Maybe<List<PolicyView>> findAll(FindPolicyQuery query) {
        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);

        QueryStringQueryBuilder queryStringQueryBuilder = QueryBuilders.queryStringQuery(query.getQueryText())
                .field("number")
                .field("policyHolder");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(queryStringQueryBuilder).size(100);

        searchRequest.source(searchSourceBuilder);

        return elasticClientAdapter
                .search(searchRequest)
                .map(this::mapSearchResponse);
    }
    
    private List<PolicyView> mapSearchResponse(SearchResponse searchResponse) {
        return Arrays
                .stream(searchResponse.getHits().getHits())
                .map(hit -> jsonConverter.objectFromString(hit.getSourceAsString(), PolicyView.class))
                .collect(Collectors.toList());
    }
    
    
}
