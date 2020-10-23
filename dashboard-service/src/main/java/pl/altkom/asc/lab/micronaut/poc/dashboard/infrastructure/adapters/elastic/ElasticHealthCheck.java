package pl.altkom.asc.lab.micronaut.poc.dashboard.infrastructure.adapters.elastic;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

@Client("${elastichealth.endpoint}")
public interface ElasticHealthCheck {
    @Get("/health")
    ElasticHealthCheckResult health();
}
