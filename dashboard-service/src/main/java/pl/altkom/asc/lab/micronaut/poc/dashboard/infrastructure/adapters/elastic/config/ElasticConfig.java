package pl.altkom.asc.lab.micronaut.poc.dashboard.infrastructure.adapters.elastic.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import javax.inject.Singleton;

import io.micronaut.context.annotation.Factory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Factory
@RequiredArgsConstructor
public class ElasticConfig {

    private final ElasticSearchSettings elasticSearchSettings;

    @Singleton
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(
                RestClient.builder(new HttpHost(elasticSearchSettings.getHost(), elasticSearchSettings.getPort()))
                        .setRequestConfigCallback(config -> config
                                .setConnectTimeout(elasticSearchSettings.getConnectionTimeout())
                                .setConnectionRequestTimeout(elasticSearchSettings.getConnectionRequestTimeout())
                                .setSocketTimeout(elasticSearchSettings.getSocketTimeout())
                        )
                        .setMaxRetryTimeoutMillis(elasticSearchSettings.getMaxRetryTimeout()));

    }

}
