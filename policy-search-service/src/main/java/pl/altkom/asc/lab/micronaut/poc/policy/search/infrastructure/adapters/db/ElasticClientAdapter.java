package pl.altkom.asc.lab.micronaut.poc.policy.search.infrastructure.adapters.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.reactivex.Maybe;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

@Singleton
@Slf4j
public class ElasticClientAdapter {
    private final RestHighLevelClient restHighLevelClient;
    private final ObjectMapper jsonMapper = new ObjectMapper();

    public ElasticClientAdapter() {
        this.restHighLevelClient = buildClient();
    }
    
    public Maybe<IndexResponse> index(IndexRequest  indexRequest) {
        return Maybe.create(sink -> {
            restHighLevelClient.indexAsync(indexRequest, new ActionListener<IndexResponse>() {
                @Override
                public void onResponse(IndexResponse indexResponse) {
                    sink.onSuccess(indexResponse);
                }

                @Override
                public void onFailure(Exception e) {
                    sink.onError(e);
                }
            });
        });
    }
    
    public Maybe<SearchResponse> search(SearchRequest searchRequest) {
        return Maybe.create(sink ->
            restHighLevelClient.searchAsync(searchRequest, new ActionListener<SearchResponse>() {
                @Override
                public void onResponse(SearchResponse searchResponse) {
                    sink.onSuccess(searchResponse);
                }

                @Override
                public void onFailure(Exception e) {
                    sink.onError(e);
                }
        }));
    }
    
    private RestHighLevelClient buildClient() {
        return new RestHighLevelClient(
                RestClient
                        .builder(new HttpHost("localhost", 9200))
                        .setRequestConfigCallback(config -> config
                                .setConnectTimeout(5_000)
                                .setConnectionRequestTimeout(5_000)
                                .setSocketTimeout(5_000)
                        )
                        .setMaxRetryTimeoutMillis(5_000));
    }
}
