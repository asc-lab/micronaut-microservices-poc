package pl.altkom.asc.lab.micronaut.poc.product.service.init;

import pl.altkom.asc.lab.micronaut.poc.product.service.domain.Choice;
import pl.altkom.asc.lab.micronaut.poc.product.service.domain.ChoiceQuestion;
import pl.altkom.asc.lab.micronaut.poc.product.service.domain.NumericQuestion;
import pl.altkom.asc.lab.micronaut.poc.product.service.domain.Product;

import java.math.BigDecimal;
import java.util.Arrays;

class DemoProductsFactory {

    static Product travel() {
        Product p = new Product(
                "TRI",
                "Safe Traveller",
                "/static/travel.jpg",
                "Travel insurance",
                10,
                "plane");

        p.addCover("C1", "Luggage", "", false, new BigDecimal("5000"));
        p.addCover("C2", "Illness", "", false, new BigDecimal("5000"));
        p.addCover("C3", "Assistance", "", true, null);

        p.addQuestions(Arrays.asList(
                new ChoiceQuestion("DESTINATION", 1, "Destination", Arrays.asList(
                        new Choice("EUR", "Europe"),
                        new Choice("WORLD", "World"),
                        new Choice("PL", "Poland")
                )),
                new NumericQuestion("NUM_OF_ADULTS", 2, "Number of adults"),
                new NumericQuestion("NUM_OF_CHILDREN", 3, "Number of children")
        ));
        return p;
    }

    static Product house() {
        Product p = new Product(
                "HSI",
                "Happy House",
                "/static/house.jpg",
                "House insurance",
                5,
                "building");

        p.addCover("C1", "Fire", "", false, new BigDecimal("200000"));
        p.addCover("C2", "Flood", "", false, new BigDecimal("100000"));
        p.addCover("C3", "Theft", "", false, new BigDecimal("50000"));
        p.addCover("C4", "Assistance", "", true, null);

        p.addQuestions(Arrays.asList(
                new ChoiceQuestion("TYP", 1, "Apartment / House", Arrays.asList(
                        new Choice("APT", "Apartment"),
                        new Choice("HOUSE", "House")
                )),
                new NumericQuestion("AREA", 2, "Area"),
                new NumericQuestion("NUM_OF_CLAIM", 3, "Number of claims in last 5 years"),
                new ChoiceQuestion("FLOOD", 4, "Located in flood risk area", ChoiceQuestion.yesNoChoice())
        ));
        return p;
    }

    static Product farm() {
        Product p = new Product(
                "FAI",
                "Happy farm",
                "/static/farm.jpg",
                "Farm insurance",
                1,
                "apple");

        p.addCover("C1", "Crops", "", false, new BigDecimal("200000"));
        p.addCover("C2", "Flood", "", false, new BigDecimal("100000"));
        p.addCover("C3", "Fire", "", false, new BigDecimal("50000"));
        p.addCover("C4", "Equipment", "", true, new BigDecimal("300000"));

        p.addQuestions(Arrays.asList(
                new ChoiceQuestion("TYP", 1, "Cultivation type", Arrays.asList(
                        new Choice("ZB", "Crop"),
                        new Choice("KW", "Vegetable")
                )),
                new NumericQuestion("AREA", 2, "Area"),
                new NumericQuestion("NUM_OF_CLAIM", 3, "Number of claims in last 5 years"),
                new ChoiceQuestion("FLOOD", 4, "Located in flood risk area", ChoiceQuestion.yesNoChoice())
        ));
        return p;
    }

    static Product car() {
        Product p = new Product(
                "CAR",
                "Happy Driver",
                "/static/car.jpg",
                "Car insurance",
                1,
                "car");

        p.addCover("C1", "Assistance", "", true, null);
        p.addQuestions(Arrays.asList(
                new NumericQuestion("NUM_OF_CLAIM", 3, "Number of claims in last 5 years")
        ));

        return p;
    }
}
