package pl.altkom.asc.lab.micronaut.poc.pricing.domain;

import java.math.BigDecimal;
import java.util.List;

public class DiscountMarkupRuleList {

    private Tariff tariff;
    private List<DiscountMarkupRule> discountMarkupRules;

    DiscountMarkupRuleList(Tariff tariff, List<DiscountMarkupRule> discountMarkupRules) {
        this.tariff = tariff;
        this.discountMarkupRules = discountMarkupRules;
    }

    public void addPercentMarkup(String applyIfFormula, BigDecimal markup){
        discountMarkupRules.add(new PercentMarkupRule(tariff, applyIfFormula, markup));
    }

    void apply(Calculation calculation) {
        discountMarkupRules
                .stream()
                .filter(r -> r.applies(calculation))
                .forEach(r -> r.apply(calculation));
    }
}
