package pl.altkom.asc.lab.micronaut.poc.pricing.domain;

import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("perc_markup")
@NoArgsConstructor
public class PercentMarkupRule extends DiscountMarkupRule {

    public PercentMarkupRule(Tariff tariff, String applyIfFormula, BigDecimal paramValue) {
        this.tariff = tariff;
        this.applyIfFormula = applyIfFormula;
        this.paramValue = paramValue;
    }

    @Override
    public Calculation apply(Calculation calculation) {
        for (Cover c : calculation.getCovers().values()) {
            c.setPrice(c.getPrice()
                    .multiply(paramValue)
                    .setScale(2, BigDecimal.ROUND_HALF_UP)
            );
        }
        return calculation;
    }


}
