package pl.altkom.asc.lab.micronaut.poc.dashboard.infrastructure.adapters.elastic;

import io.micronaut.context.annotation.ConfigurationProperties;
import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties("elastic")
@Getter
@Setter
class ElasticSearchSettings {
    private String host;
    private int port;
    private int connectionTimeout;
    private int connectionRequestTimeout;
    private int socketTimeout;
    private int maxRetryTimeout;
}
