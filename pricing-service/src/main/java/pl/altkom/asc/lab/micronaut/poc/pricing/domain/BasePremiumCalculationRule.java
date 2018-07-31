package pl.altkom.asc.lab.micronaut.poc.pricing.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mvel2.MVEL;

import java.math.BigDecimal;
import java.util.Map;

@NoArgsConstructor
@Getter
public class BasePremiumCalculationRule {
    private String coverCode;
    private String applyIfFormula;
    private String basePriceFormula;

    public BasePremiumCalculationRule(String coverCode, String applyIfFormula, String basePriceFormula) {
        this.coverCode = coverCode;
        this.applyIfFormula = applyIfFormula;
        this.basePriceFormula = basePriceFormula;
    }

    public boolean applies(Calculation calculation){
        return applyIfFormula==null || applyIfFormula.isEmpty() ? true : MVEL.eval(applyIfFormula, calculation.toMap(), Boolean.class);
    }

    public BigDecimal calculateBasePrice(Calculation calculation){
        return MVEL.eval(basePriceFormula, calculation.toMap(), BigDecimal.class);
    }
}
