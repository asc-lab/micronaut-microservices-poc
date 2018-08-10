package pl.altkom.asc.lab.micronaut.poc.pricing.domain;

import java.math.BigDecimal;

class TariffsFactory {

    static Tariff travel() {
        Tariff t = new Tariff("TRI");

        t.rules().addBasePriceRule("C1", null, "(NUM_OF_ADULTS) * (DESTINATION == 'EUR' ? 26.00B : 34.00B)");
        t.rules().addBasePriceRule("C2", null, "(NUM_OF_ADULTS + NUM_OF_CHILDREN) * 26.00B");
        t.rules().addBasePriceRule("C3", null, "(NUM_OF_ADULTS + NUM_OF_CHILDREN) * 10.00B");

        t.discountMarkupRules().addPercentMarkup("DESTINATION == 'WORLD'", new BigDecimal("1.50"));

        return t;
    }

    static Tariff house() {
        Tariff t = new Tariff("HSI");

        t.rules().addBasePriceRule("C1", "TYP == 'APT'", "AREA * 1.25B");
        t.rules().addBasePriceRule("C1", "TYP == 'HOUSE'", "AREA * 1.50B");

        t.rules().addBasePriceRule("C2", "TYP == 'APT'", "AREA * 0.25B");
        t.rules().addBasePriceRule("C2", "TYP == 'HOUSE'", "AREA * 0.45B");

        t.rules().addBasePriceRule("C3", null, "30B");
        t.rules().addBasePriceRule("C4", null, "50B");

        t.discountMarkupRules().addPercentMarkup("FLOOD == 'YES'", new BigDecimal("1.50"));
        t.discountMarkupRules().addPercentMarkup("NUM_OF_CLAIM > 1 ", new BigDecimal("1.25"));

        return t;
    }

    static Tariff car() {
        Tariff t = new Tariff("CAR");

        t.rules().addBasePriceRule("C1", null, "100B");
        t.discountMarkupRules().addPercentMarkup("NUM_OF_CLAIM > 2", new BigDecimal("50.00"));

        return t;
    }
}
