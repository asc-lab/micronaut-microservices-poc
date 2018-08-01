package pl.altkom.asc.lab.micronaut.poc.pricing.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "tariff")
@NoArgsConstructor
@Getter
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "code")
    private String code;
    @ElementCollection
    @CollectionTable(name = "base_price_rules", joinColumns = @JoinColumn(name = "tariff_id"))
    private List<BasePremiumCalculationRule> basePriceCalculationRules;
    @OneToMany(mappedBy = "tariff", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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
