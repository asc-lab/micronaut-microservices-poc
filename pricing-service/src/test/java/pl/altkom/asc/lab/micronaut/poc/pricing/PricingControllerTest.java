package pl.altkom.asc.lab.micronaut.poc.pricing;

import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.commands.calculateprice.CalculatePriceCommand;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.commands.calculateprice.CalculatePriceResult;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.commands.calculateprice.dto.ChoiceQuestionAnswer;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.commands.calculateprice.dto.NumericQuestionAnswer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PricingControllerTest {

    private static EmbeddedServer server;
    private static PricingTestClient client;

    @BeforeClass
    public static void setup() {
        server = ApplicationContext.run(EmbeddedServer.class);
        client = server.getApplicationContext().createBean(PricingTestClient.class, server.getURL());
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionWhenCommandIsNull() {
        client.calculatePrice(null);
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
        assertEquals("Total premium should be 78", new BigDecimal("78"), result.getTotalPrice());
        assertEquals("C1 premium should be 26", new BigDecimal("26"), result.getCoversPrices().get("C1"));
        assertEquals("C2 should be 52", new BigDecimal("52"), result.getCoversPrices().get("C2"));
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
        assertEquals("Total premium should be 172.50", new BigDecimal("172.50"), result.getTotalPrice());
        assertEquals("C1 premium should be 118.75", new BigDecimal("118.75"), result.getCoversPrices().get("C1"));
        assertEquals("C2 should be 23.75", new BigDecimal("23.75"), result.getCoversPrices().get("C2"));
        assertEquals("C3 should be 30", new BigDecimal("30"), result.getCoversPrices().get("C3"));
    }

    @AfterClass
    public static void cleanup() {
        if (server != null)
            server.stop();
    }
}
