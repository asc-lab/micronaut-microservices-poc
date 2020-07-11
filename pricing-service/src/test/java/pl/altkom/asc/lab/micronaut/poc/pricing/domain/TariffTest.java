package pl.altkom.asc.lab.micronaut.poc.pricing.domain;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TariffTest {
    @Test
    public void canCalculateTravelPolicyPrice() {
        Map<String, Object> subject = new HashMap<>();
        subject.put("NUM_OF_ADULTS", new BigDecimal("1"));
        subject.put("NUM_OF_CHILDREN", new BigDecimal("1"));
        subject.put("DESTINATION", "EUR");

        Calculation calculation = new Calculation(
                "TRI",
                LocalDate.of(2017, 4, 16),
                LocalDate.of(2017, 4, 20),
                Arrays.asList("C1", "C2", "C3"),
                subject);

        Tariff tariff = TariffsFactory.travel();

        calculation = tariff.calculatePrice(calculation);

        assertEquals(new BigDecimal("98.00"), calculation.getTotalPremium(), "Total premium should be 78");
        assertEquals(new BigDecimal("26.00"), calculation.getCovers().get("C1").getPrice(), "C1 premium should be 26");
        assertEquals(new BigDecimal("52.00"), calculation.getCovers().get("C2").getPrice(), "C2 should be 52");
        assertEquals(new BigDecimal("20.00"), calculation.getCovers().get("C3").getPrice(),"C3 should be 20");
    }

    @Test
    public void canCalculateHousePolicyPrice() {
        Map<String, Object> subject = new HashMap<>();
        subject.put("TYP", "APT");
        subject.put("AREA", new BigDecimal("95"));
        subject.put("NUM_OF_CLAIM", 1);
        subject.put("FLOOD", "NO");

        Calculation calculation = new Calculation(
                "HSI",
                LocalDate.of(2017, 4, 16),
                LocalDate.of(2018, 4, 15),
                Arrays.asList("C1", "C2", "C3"),
                subject);

        Tariff tariff = TariffsFactory.house();

        calculation = tariff.calculatePrice(calculation);

        assertEquals(new BigDecimal("172.50"), calculation.getTotalPremium(),"Total premium should be 172.50");
        assertEquals(new BigDecimal("118.75"), calculation.getCovers().get("C1").getPrice(),"C1 premium should be 118.75");
        assertEquals(new BigDecimal("23.75"), calculation.getCovers().get("C2").getPrice(),"C2 should be 23.75");
        assertEquals(new BigDecimal("30"), calculation.getCovers().get("C3").getPrice(),"C3 should be 30");
    }

    @Test
    public void canCalculateCarPolicyPrice() {
        Map<String, Object> subject = new HashMap<>();
        subject.put("NUM_OF_CLAIM", 1);

        Calculation calculation = new Calculation(
                "CAR",
                LocalDate.of(2017, 4, 16),
                LocalDate.of(2018, 4, 15),
                Collections.singletonList("C1"),
                subject);

        Tariff tariff = TariffsFactory.car();

        calculation = tariff.calculatePrice(calculation);

        assertEquals(new BigDecimal("100"), calculation.getTotalPremium(),"Total premium should be 100");
        assertEquals(new BigDecimal("100"), calculation.getCovers().get("C1").getPrice(),"C1 premium should be 100");
    }
}
