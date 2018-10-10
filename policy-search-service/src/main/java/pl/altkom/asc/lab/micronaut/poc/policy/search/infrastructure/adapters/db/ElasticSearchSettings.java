package pl.altkom.asc.lab.micronaut.poc.policy.search.infrastructure.adapters.db;

import io.micronaut.context.annotation.ConfigurationProperties;
import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties("elastic")
@Getter
@Setter
public class ElasticSearchSettings {
    private String host;
    private int port;
    private int connectionTimeout;
    private int connectionRequestTimeout;
    private int socketTimeout;
    private int maxRetryTimeout;
}
