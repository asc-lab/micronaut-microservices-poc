package pl.altkom.asc.lab.micronaut.poc.pricing.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mvel2.MVEL;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@DiscriminatorColumn(name = "type")
@Table(name = "discount_markup_rule")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@Getter
public abstract class DiscountMarkupRule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tariff_id")
    protected Tariff tariff;

    @Column(name = "apply_if_formula")
    protected String applyIfFormula;

    @Column(name = "param_value")
    protected BigDecimal paramValue;

    boolean applies(Calculation calculation) {
        return applyIfFormula == null || applyIfFormula.isEmpty()
                ? true
                : MVEL.eval(applyIfFormula, calculation.toMap(), Boolean.class);
    }

    public abstract Calculation apply(Calculation calculation);
}
