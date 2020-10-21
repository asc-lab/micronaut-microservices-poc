package pl.altkom.asc.lab.micronaut.poc.dashboard.elastic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import pl.altkom.asc.lab.micronaut.poc.dashboard.domain.AgentSalesQuery;
import pl.altkom.asc.lab.micronaut.poc.dashboard.domain.LocalDateRange;
import pl.altkom.asc.lab.micronaut.poc.dashboard.domain.PolicyDocument;
import pl.altkom.asc.lab.micronaut.poc.dashboard.infrastructure.adapters.elastic.PolicyElasticRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PolicyElasticRepositoryGetAgentSalesTest extends EmbeddedElasticTest {

    @BeforeAll
    public void seedData() {
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
                        "a.smith"),
                new PolicyDocument(
                        "111-003",
                        LocalDate.of(2019, 2, 1),
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
                        "a.smith"),
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
    public void canFindAgentSales() {
        AgentSalesQuery.Result agentSales = policyElasticRepository().getAgentSales(AgentSalesQuery.builder()
                .filterByProductCode(null)
                .filterBySalesDate(LocalDateRange.between(LocalDate.of(2019,1,1),LocalDate.of(2019,12,31)))
                .build()
        );

        assertEquals(Long.valueOf(3L), agentSales.getPerAgentTotal().get("m.smith").getPoliciesCount());
        assertEquals(new BigDecimal("3000.0"), agentSales.getPerAgentTotal().get("m.smith").getPremiumAmount());

        assertEquals(Long.valueOf(2L), agentSales.getPerAgentTotal().get("a.smith").getPoliciesCount());
        assertEquals(new BigDecimal("2000.0"), agentSales.getPerAgentTotal().get("a.smith").getPremiumAmount());
    }

    @Test
    public void canFindAgentSalesFilteredByProduct() {
        AgentSalesQuery.Result agentSales = policyElasticRepository().getAgentSales(AgentSalesQuery.builder()
                .filterByProductCode("SAFE_HOUSE")
                .filterBySalesDate(LocalDateRange.between(LocalDate.of(2019,1,1),LocalDate.of(2019,12,31)))
                .build()
        );

        assertEquals(Long.valueOf(1L), agentSales.getPerAgentTotal().get("m.smith").getPoliciesCount());
        assertEquals(new BigDecimal("1000.0"), agentSales.getPerAgentTotal().get("m.smith").getPremiumAmount());
    }

    @Test
    public void canFindAgentSalesFilteredBySalesDates() {
        AgentSalesQuery.Result agentSales = policyElasticRepository().getAgentSales(AgentSalesQuery.builder()
                .filterByProductCode(null)
                .filterBySalesDate(LocalDateRange.between(LocalDate.of(2019,1,1),LocalDate.of(2019,3,31)))
                .build()
        );

        assertEquals(Long.valueOf(2L), agentSales.getPerAgentTotal().get("m.smith").getPoliciesCount());
        assertEquals(new BigDecimal("2000.0"), agentSales.getPerAgentTotal().get("m.smith").getPremiumAmount());

        assertEquals(Long.valueOf(2L), agentSales.getPerAgentTotal().get("a.smith").getPoliciesCount());
        assertEquals(new BigDecimal("2000.0"), agentSales.getPerAgentTotal().get("a.smith").getPremiumAmount());
    }
}
