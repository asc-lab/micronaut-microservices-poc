package pl.altkom.asc.lab.micronaut.poc.dashboard.elastic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.allegro.tech.embeddedelasticsearch.EmbeddedElastic;
import pl.altkom.asc.lab.micronaut.poc.dashboard.domain.LocalDateRange;
import pl.altkom.asc.lab.micronaut.poc.dashboard.domain.PolicyDocument;
import pl.altkom.asc.lab.micronaut.poc.dashboard.domain.SalesTrendsQuery;
import pl.altkom.asc.lab.micronaut.poc.dashboard.domain.TimeAggregationUnit;
import pl.altkom.asc.lab.micronaut.poc.dashboard.infrastructure.adapters.elastic.config.JsonConverter;
import pl.altkom.asc.lab.micronaut.poc.dashboard.infrastructure.adapters.elastic.PolicyElasticRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PolicyElasticRepositoryGetSalesTrendsTest {
    static EmbeddedElastic el = DashboardEmbeddedElastic.getInstance();

    @BeforeAll
    public static void seedData() {
        List<PolicyDocument> docs = Arrays.asList(
                new PolicyDocument(
                        "111-001",
                        LocalDate.of(2019, 1, 1),
                        LocalDate.of(2019, 12, 31),
                        "John Smith",
                        "SAFE_HOUSE",
                        BigDecimal.valueOf(1000),
                        "m.smith"),
                new PolicyDocument(
                        "111-002",
                        LocalDate.of(2019, 2, 1),
                        LocalDate.of(2020, 1, 31),
                        "John Smith",
                        "SAFE_HOUSE",
                        BigDecimal.valueOf(1000),
                        "m.smith"),
                new PolicyDocument(
                        "111-003",
                        LocalDate.of(2019, 2, 2),
                        LocalDate.of(2020, 2, 28),
                        "John Smith",
                        "SAFE_CAR",
                        BigDecimal.valueOf(1000),
                        "m.smith"),
                new PolicyDocument(
                        "111-004",
                        LocalDate.of(2019, 3, 1),
                        LocalDate.of(2020, 3, 31),
                        "John Smith",
                        "SAFE_CAR",
                        BigDecimal.valueOf(1000),
                        "m.smith"),
                new PolicyDocument(
                        "111-005",
                        LocalDate.of(2019, 4, 1),
                        LocalDate.of(2020, 4, 30),
                        "John Smith",
                        "SAFE_FARM",
                        BigDecimal.valueOf(1000),
                        "m.smith")
        );

        PolicyElasticRepository repository = policyElasticRepository();

        docs.forEach(d -> repository.save(d));
    }

    @Test
    public void canFindSalesTrends() {
        SalesTrendsQuery.Result trends = policyElasticRepository().getSalesTrends(SalesTrendsQuery.builder()
                .filterByProductCode(null)
                .filterBySalesDate(LocalDateRange.between(LocalDate.of(2019,1,1),LocalDate.of(2019,12,31)))
                .aggregationUnit(TimeAggregationUnit.MONTH)
                .build());

        assertEquals(4, trends.getPeriodSales().size());

        assertEquals("2019-01-01", trends.getPeriodSales().get(0).getPeriodDate().toString());
        assertEquals(Long.valueOf(1L), trends.getPeriodSales().get(0).getSales().getPoliciesCount());
        assertEquals(new BigDecimal("1000.00"), trends.getPeriodSales().get(0).getSales().getPremiumAmount());

        assertEquals("2019-02-01", trends.getPeriodSales().get(1).getPeriodDate().toString());
        assertEquals(Long.valueOf(2L), trends.getPeriodSales().get(1).getSales().getPoliciesCount());
        assertEquals(new BigDecimal("2000.00"), trends.getPeriodSales().get(1).getSales().getPremiumAmount());

        assertEquals("2019-03-01", trends.getPeriodSales().get(2).getPeriodDate().toString());
        assertEquals(Long.valueOf(1L), trends.getPeriodSales().get(2).getSales().getPoliciesCount());
        assertEquals(new BigDecimal("1000.00"), trends.getPeriodSales().get(2).getSales().getPremiumAmount());

        assertEquals("2019-04-01", trends.getPeriodSales().get(3).getPeriodDate().toString());
        assertEquals(Long.valueOf(1L), trends.getPeriodSales().get(3).getSales().getPoliciesCount());
        assertEquals(new BigDecimal("1000.00"), trends.getPeriodSales().get(3).getSales().getPremiumAmount());
    }

    @Test
    public void canFindSalesTrendsFilteredByProduct() {
        SalesTrendsQuery.Result trends = policyElasticRepository().getSalesTrends(SalesTrendsQuery.builder()
                .filterByProductCode("SAFE_HOUSE")
                .filterBySalesDate(LocalDateRange.between(LocalDate.of(2019,1,1),LocalDate.of(2019,12,31)))
                .aggregationUnit(TimeAggregationUnit.MONTH)
                .build());

        assertEquals(2, trends.getPeriodSales().size());

        assertEquals("2019-01-01", trends.getPeriodSales().get(0).getPeriodDate().toString());
        assertEquals(Long.valueOf(1L), trends.getPeriodSales().get(0).getSales().getPoliciesCount());
        assertEquals(new BigDecimal("1000.00"), trends.getPeriodSales().get(0).getSales().getPremiumAmount());

        assertEquals("2019-02-01", trends.getPeriodSales().get(1).getPeriodDate().toString());
        assertEquals(Long.valueOf(1L), trends.getPeriodSales().get(1).getSales().getPoliciesCount());
        assertEquals(new BigDecimal("1000.00"), trends.getPeriodSales().get(1).getSales().getPremiumAmount());

    }


    @Test
    public void canFindSalesTrendsFilteredBySalesDates() {
        SalesTrendsQuery.Result trends = policyElasticRepository().getSalesTrends(SalesTrendsQuery.builder()
                .filterByProductCode(null)
                .filterBySalesDate(LocalDateRange.between(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 31)))
                .aggregationUnit(TimeAggregationUnit.MONTH)
                .build());

        assertEquals(1, trends.getPeriodSales().size());

        assertEquals("2019-01-01", trends.getPeriodSales().get(0).getPeriodDate().toString());
        assertEquals(Long.valueOf(1L), trends.getPeriodSales().get(0).getSales().getPoliciesCount());
        assertEquals(new BigDecimal("1000.00"), trends.getPeriodSales().get(0).getSales().getPremiumAmount());

    }

    private static PolicyElasticRepository policyElasticRepository() {
        return new PolicyElasticRepository(
                new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", el.getHttpPort(), "http"))),
                new JsonConverter(objectMapper())
        );
    }

    private static ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }
}
