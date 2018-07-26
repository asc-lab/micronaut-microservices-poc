package pl.altkom.asc.lab.micronaut.poc.policy.domain;

import lombok.*;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.vo.MonetaryAmount;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.vo.Quantity;

import javax.persistence.*;


@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class Limit {
    @Enumerated(EnumType.STRING)
    private LimitPeriod period;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "LIMIT_AMOUNT"))
    })
    private MonetaryAmount maxAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "LIMIT_QT"))
    })
    private Quantity maxQuantity;

    static Limit amountForPolicyYear(MonetaryAmount maxAmount) {
        return new Limit(LimitPeriod.POLICY_YEAR, maxAmount, null);
    }

    static Limit quantityForPolicyYear(Quantity maxQuantity) {
        return new Limit(LimitPeriod.POLICY_YEAR, null, maxQuantity);
    }

    static Limit amountAndQuantityForPolicyYear(MonetaryAmount maxAmount, Quantity maxQuantity) {
        return new Limit(LimitPeriod.POLICY_YEAR, maxAmount, maxQuantity);
    }

    static Limit empty() {
        return new Limit(null,null,null);
    }

    MonetaryAmount calculate(Quantity qt, MonetaryAmount price, MonetaryAmount coPayment, Quantity consumedQt, MonetaryAmount consumedAmount) {
        Quantity limitQt = maxQuantity!=null ?
                Quantity.max(maxQuantity.subtract(consumedQt), Quantity.zero())
                : null;

        MonetaryAmount limitAmount = maxAmount!=null ?
                MonetaryAmount.max(maxAmount.subtract(consumedAmount), MonetaryAmount.zero())
                : null;

        MonetaryAmount priceAfterCoPayment = price
                .subtract(coPayment.divide(qt));

        Quantity qtToPayByInsurer = qt;

        if (limitQt!=null) {
            qtToPayByInsurer = Quantity.min(limitQt, qtToPayByInsurer);
        }

        MonetaryAmount amtToPayByInsurer = qtToPayByInsurer.multiply(priceAfterCoPayment);

        if (limitAmount!=null) {
            amtToPayByInsurer = MonetaryAmount.min(limitAmount, amtToPayByInsurer);
        }

        return amtToPayByInsurer;
    }
}
