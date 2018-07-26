package pl.altkom.asc.lab.micronaut.poc.policy.domain.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Quantity {
    private BigDecimal value;

    public static Quantity of(BigDecimal value) {
        return new Quantity(value);
    }

    public static Quantity zero() {
        return new Quantity(BigDecimal.ZERO);
    }

    public MonetaryAmount multiply(MonetaryAmount amount) {
        return amount.multiply(value);
    }

    public static Quantity min(Quantity q1, Quantity q2) {
        return q1.value.compareTo(q2.value) >= 1 ? q2 : q1;
    }

    public static Quantity max(Quantity q1, Quantity q2) {
        return q1.value.compareTo(q2.value) >= 1 ? q1 : q2;
    }

    public Quantity add(Quantity qt){
        return new Quantity(qt.value.add(value));
    }

    public Quantity subtract(Quantity qt) { return new Quantity(value.subtract(qt.value)); }
}
