package pl.altkom.asc.lab.micronaut.poc.policy.domain;

import lombok.*;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.vo.MonetaryAmount;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.vo.Percent;

import javax.persistence.*;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class CoPayment {
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "CO_PAYMENT_AMOUNT"))
    })
    private MonetaryAmount amount;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "CO_PAYMENT_VALUE"))
    })
    private Percent percent;

    static CoPayment amount(MonetaryAmount amount) {
        return new CoPayment(amount, null);
    }

    static CoPayment percent(Percent percent) {
        return new CoPayment(null, percent);
    }

    static CoPayment empty() {
        return new CoPayment(MonetaryAmount.zero(), null);
    }

    public MonetaryAmount calculate(MonetaryAmount cost) {
        if (percent != null) {
            return cost.multiply(percent);
        }

        if (amount != null) {
            return MonetaryAmount.min(cost, amount);
        }

        return MonetaryAmount.zero();
    }
}
