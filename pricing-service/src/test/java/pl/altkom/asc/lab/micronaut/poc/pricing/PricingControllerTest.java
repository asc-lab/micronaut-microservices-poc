package pl.altkom.asc.lab.micronaut.poc.pricing;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.commands.calculateprice.CalculatePriceCommand;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.commands.calculateprice.CalculatePriceResult;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.commands.calculateprice.dto.ChoiceQuestionAnswer;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.commands.calculateprice.dto.NumericQuestionAnswer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import javax.validation.ConstraintViolationException;

import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.server.EmbeddedServer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class PricingControllerTest {

    private static EmbeddedServer server;
    private static PricingTestClient client;

    @BeforeAll
    public static void setup() {
        server = ApplicationContext.run(EmbeddedServer.class);
        client = server.getApplicationContext().createBean(PricingTestClient.class, server.getURL());
    }

    @Test
    public void exceptionWhenCommandIsNull() {
        assertThrows(ConstraintViolationException.class, () -> client.calculatePrice(null));
    }

    @Test
    public void canCalculateTravelPolicyPrice() {
        CalculatePriceCommand cmd = new CalculatePriceCommand(
                "TRI",
                LocalDate.of(2017, 4, 16),
                LocalDate.of(2018, 4, 15),
                Arrays.asList("C1", "C2"),
                Arrays.asList(
                        new NumericQuestionAnswer("NUM_OF_ADULTS", new BigDecimal("1")),
                        new NumericQuestionAnswer("NUM_OF_CHILDREN", new BigDecimal("1")),
                        new ChoiceQuestionAnswer("DESTINATION", "EUR")
                )
        );

        CalculatePriceResult result = client.calculatePrice(cmd);

        assertNotNull(result);
        assertEquals(new BigDecimal("78"), result.getTotalPrice(),"Total premium should be 78");
        assertEquals(new BigDecimal("26"), result.getCoversPrices().get("C1"),"C1 premium should be 26");
        assertEquals(new BigDecimal("52"), result.getCoversPrices().get("C2"),"C2 should be 52");
    }

    @Test
    public void canCalculateHousePolicyPrice() {
        CalculatePriceCommand cmd = new CalculatePriceCommand(
                "HSI",
                LocalDate.of(2017, 4, 16),
                LocalDate.of(2018, 4, 15),
                Arrays.asList("C1", "C2", "C3"),
                Arrays.asList(
                        new NumericQuestionAnswer("AREA", new BigDecimal("95")),
                        new NumericQuestionAnswer("NUM_OF_CLAIM", new BigDecimal("1")),
                        new ChoiceQuestionAnswer("FLOOD", "NO"),
                        new ChoiceQuestionAnswer("TYP", "APT")
                )
        );

        CalculatePriceResult result = client.calculatePrice(cmd);

        assertNotNull(result);
        assertEquals(new BigDecimal("172.50"), result.getTotalPrice(),"Total premium should be 172.50");
        assertEquals(new BigDecimal("118.75"), result.getCoversPrices().get("C1"),"C1 premium should be 118.75");
        assertEquals(new BigDecimal("23.75"), result.getCoversPrices().get("C2"),"C2 should be 23.75");
        assertEquals(new BigDecimal("30"), result.getCoversPrices().get("C3"),"C3 should be 30");
    }

    @AfterAll
    public static void cleanup() {
        if (server != null)
            server.stop();
    }
}
