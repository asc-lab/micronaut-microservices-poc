package pl.altkom.asc.lab.micronaut.poc.policy.search.infrastructure.adapters.db;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.reactivex.Maybe;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import pl.altkom.asc.lab.micronaut.poc.policy.search.readmodel.PolicyView;
import pl.altkom.asc.lab.micronaut.poc.policy.search.readmodel.PolicyViewRepository;
import pl.altkom.asc.lab.micronaut.poc.policy.search.service.api.v1.queries.findpolicy.FindPolicyQuery;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class ElasticPolicyViewRepository implements PolicyViewRepository {
    private final ElasticClientAdapter elasticClientAdapter;
    private final JsonConverter jsonConverter;
    
    @Override
    public void save(PolicyView policy) {
        IndexRequest indexRequest = new IndexRequest("policy-views","policyview", policy.getNumber());
        indexRequest.source(jsonConverter.stringifyObject(policy), XContentType.JSON);
        elasticClientAdapter.index(indexRequest).blockingGet();
    }
    
    @Override
    public Maybe<List<PolicyView>> findAll(FindPolicyQuery query) {
        SearchRequest searchRequest = new SearchRequest("policy-views");
        MultiMatchQueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(query.getQueryText(),
                "number",
                "policyHolder")
                .type(MultiMatchQueryBuilder.Type.BEST_FIELDS)
                .fuzziness(Fuzziness.AUTO);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(queryBuilder).size(10);

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
