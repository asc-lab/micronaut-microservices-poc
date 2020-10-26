package pl.altkom.asc.lab.micronaut.poc.dashboard.elastic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import pl.allegro.tech.embeddedelasticsearch.EmbeddedElastic;
import pl.altkom.asc.lab.micronaut.poc.dashboard.infrastructure.adapters.elastic.PolicyElasticRepository;
import pl.altkom.asc.lab.micronaut.poc.dashboard.infrastructure.adapters.elastic.config.JsonConverter;

public class EmbeddedElasticTest {
    protected EmbeddedElastic el = DashboardEmbeddedElastic.getInstance();

    protected PolicyElasticRepository policyElasticRepository() {
        return new PolicyElasticRepository(
                new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", el.getHttpPort(), "http"))),
                new JsonConverter(objectMapper())
        );
    }

    protected ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }
}
