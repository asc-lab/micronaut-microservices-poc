package pl.altkom.asc.lab.micronaut.poc.pricing.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mvel2.MVEL;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
public abstract class DiscountMarkupRule {
    private Long id;
    protected Tariff tariff;
    protected String applyIfFormula;
    protected BigDecimal paramValue;

    public boolean applies(Calculation calculation){
        return applyIfFormula!=null & !applyIfFormula.isEmpty() ? true : MVEL.eval(applyIfFormula, calculation.toMap(), Boolean.class);
    }

    public abstract Calculation apply(Calculation calculation);
}
