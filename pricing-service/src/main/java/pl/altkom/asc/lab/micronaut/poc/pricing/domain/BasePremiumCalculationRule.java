package pl.altkom.asc.lab.micronaut.poc.pricing.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mvel2.MVEL;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
@NoArgsConstructor
@Getter
public class BasePremiumCalculationRule {

    @Column(name = "cover_code")
    private String coverCode;

    @Column(name = "apply_if_formula")
    private String applyIfFormula;

    @Column(name = "price_formula")
    private String basePriceFormula;

    public BasePremiumCalculationRule(String coverCode, String applyIfFormula, String basePriceFormula) {
        this.coverCode = coverCode;
        this.applyIfFormula = applyIfFormula;
        this.basePriceFormula = basePriceFormula;
    }

    public boolean applies(Calculation calculation) {
        return applyIfFormula == null || applyIfFormula.isEmpty()
                ? true
                : MVEL.eval(applyIfFormula, calculation.toMap(), Boolean.class);
    }

    public BigDecimal calculateBasePrice(Calculation calculation) {
        return MVEL.eval(basePriceFormula, calculation.toMap(), BigDecimal.class);
    }
}
