package pl.altkom.asc.lab.micronaut.poc.dashboard.elastic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import pl.allegro.tech.embeddedelasticsearch.EmbeddedElastic;
import pl.altkom.asc.lab.micronaut.poc.dashboard.domain.PolicyDocument;
import pl.altkom.asc.lab.micronaut.poc.dashboard.infrastructure.adapters.elastic.JsonConverter;
import pl.altkom.asc.lab.micronaut.poc.dashboard.infrastructure.adapters.elastic.PolicyElasticRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PolicyElasticRepositoryTest {
    EmbeddedElastic el = DashboardEmbeddedElastic.getInstance();

    @Test
    public void canIndexPolicy() {
        PolicyDocument policyDocument = new PolicyDocument(
                "111-111",
                LocalDate.of(2019, 1, 1),
                LocalDate.of(2019, 12, 31),
                "John Smith",
                "SAFE_HOUSE",
                BigDecimal.valueOf(1000),
                "m.smith"
        );

        PolicyElasticRepository repository = new PolicyElasticRepository(
                new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", el.getHttpPort(), "http"))),
                new JsonConverter(objectMapper())
        );

        repository.save(policyDocument);

        PolicyDocument saved = repository.findByNumber("111-111");

        assertNotNull(saved);
    }

    private ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }
}
