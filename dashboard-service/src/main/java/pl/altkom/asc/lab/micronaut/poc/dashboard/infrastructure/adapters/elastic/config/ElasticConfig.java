package pl.altkom.asc.lab.micronaut.poc.dashboard.infrastructure.adapters.elastic.config;

import io.micronaut.context.annotation.Factory;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import javax.inject.Singleton;

@Factory
@RequiredArgsConstructor
public class ElasticConfig {

    private final ElasticSearchSettings elasticSearchSettings;

    @Singleton
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(RestClient.builder(
                new HttpHost(
                        elasticSearchSettings.getHost(),
                        elasticSearchSettings.getPort(),
                        "http"
                )
        ));
    }

}
