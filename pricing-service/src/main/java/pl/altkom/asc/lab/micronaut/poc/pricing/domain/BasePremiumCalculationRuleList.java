package pl.altkom.asc.lab.micronaut.poc.pricing.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class BasePremiumCalculationRuleList {

    private List<BasePremiumCalculationRule> basePriceCalculationRules;

    public BasePremiumCalculationRuleList(List<BasePremiumCalculationRule> basePriceCalculationRules) {
        this.basePriceCalculationRules = basePriceCalculationRules;
    }

    public BasePremiumCalculationRule addBasePriceRule(String coverCode, String applyIfFormula, String basePriceFormula) {
        BasePremiumCalculationRule rule = new BasePremiumCalculationRule(coverCode, applyIfFormula, basePriceFormula);
        basePriceCalculationRules.add(rule);
        return rule;
    }

    public List<BasePremiumCalculationRule> getRulesFor(String coverCode) {
        return basePriceCalculationRules
                .stream()
                .filter(r -> r.getCoverCode().equals(coverCode))
                .collect(Collectors.toList());
    }

    public BigDecimal calculateBasePriceFor(Cover cover, Calculation calculation) {
        return getRulesFor(cover.getCode())
                .stream()
                .filter(r -> r.applies(calculation))
                .map(r -> r.calculateBasePrice(calculation))
                .findFirst()
                .orElse(null);
    }
}
