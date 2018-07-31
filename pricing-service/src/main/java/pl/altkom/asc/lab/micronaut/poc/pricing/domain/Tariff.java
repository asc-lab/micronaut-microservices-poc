package pl.altkom.asc.lab.micronaut.poc.pricing.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class Tariff {
    private Long id;
    private String code;
    private List<BasePremiumCalculationRule> basePriceCalculationRules;
    private List<DiscountMarkupRule> discountMarkupRules;

    public Tariff(Long id, String code) {
        this.id = id;
        this.code = code;
        this.basePriceCalculationRules = new ArrayList<>();
        this.discountMarkupRules = new ArrayList<>();
    }

    public BasePremiumCalculationRuleList rules(){
        return new BasePremiumCalculationRuleList(basePriceCalculationRules);
    }

    public DiscountMarkupRuleList discountMarkupRules(){
        return new DiscountMarkupRuleList(this,discountMarkupRules);
    }

    public Calculation calculatePrice(Calculation calculation){
        //calc base prices
        for (Cover c : calculation.getCovers().values()){
            c.setPrice(rules().calculateBasePriceFor(c, calculation));
        }
        //apply discounts
        discountMarkupRules().apply(calculation);
        //build response
        calculation.updateTotal();
        return calculation;
    }
}
