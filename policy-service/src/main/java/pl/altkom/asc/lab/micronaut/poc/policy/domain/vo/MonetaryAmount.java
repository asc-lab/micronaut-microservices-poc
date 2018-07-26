package pl.altkom.asc.lab.micronaut.poc.policy.domain.vo;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Embeddable
@Getter
public class MonetaryAmount implements Comparable<MonetaryAmount>  {
    private final BigDecimal amount;

    public MonetaryAmount(BigDecimal amount) {
        this.amount = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static MonetaryAmount zero() {
        return from(new BigDecimal("0.00"));
    }

    public static MonetaryAmount from(BigDecimal amount) {
        if (amount == null) {
            throw new RuntimeException("Amount for MonetaryAmount cannot be null");
        }
        return new MonetaryAmount(amount);
    }

    public static MonetaryAmount from(String amount) {
        if (amount == null) {
            throw new RuntimeException("Amount for MonetaryAmount cannot be null");
        }
        return new MonetaryAmount(new BigDecimal(amount));
    }

    public static MonetaryAmount from(Long amount) {
        if (amount == null) {
            throw new RuntimeException("Amount for MonetaryAmount cannot be null");
        }
        return new MonetaryAmount(new BigDecimal(amount));
    }

    protected MonetaryAmount(){
        this.amount = BigDecimal.ZERO;
    }

    public MonetaryAmount add(MonetaryAmount monetaryAmount) {
        if (monetaryAmount == null) {
            throw new RuntimeException("Cant add null MonetaryAmount");
        }
        return new MonetaryAmount(amount.add(monetaryAmount.toBigDecimal()));
    }

    public MonetaryAmount subtract(MonetaryAmount monetaryAmount) {
        if (monetaryAmount == null) {
            throw new RuntimeException("Cant subtract null MonetaryAmount");
        }

        return new MonetaryAmount(amount.subtract(monetaryAmount.toBigDecimal()));
    }

    public boolean isPositive() {
        return amount.signum() == 1;
    }

    public boolean isNegative() {
        return amount.signum() == -1;
    }

    public boolean isZero() {
        return amount.signum() == 0;
    }

    public boolean isPositiveOrZero() {
        return isPositive() || isZero();
    }

    public boolean isNegativeOrZero() {
        return isNegative() || isZero();
    }

    public boolean greaterThan(MonetaryAmount monetaryAmount) {
        return this.compareTo(monetaryAmount) == 1;
    }

    public boolean greaterOrEqual(MonetaryAmount monetaryAmount) {
        return this.compareTo(monetaryAmount) >= 0;
    }

    public boolean lowerThan(MonetaryAmount monetaryAmount) {
        return this.compareTo(monetaryAmount) == -1;
    }

    public boolean lowerOrEqual(MonetaryAmount monetaryAmount) {

        return this.compareTo(monetaryAmount) <= 0;
    }

    public boolean equalTo(MonetaryAmount monetaryAmount) {
        return this.compareTo(monetaryAmount) == 0;
    }

    public MonetaryAmount toWholeNumber() {
        return new MonetaryAmount(amount.setScale(0, RoundingMode.HALF_UP));
    }

    public MonetaryAmount round(int numerOfDecimalPlaces) {
        return new MonetaryAmount(amount.setScale(numerOfDecimalPlaces, RoundingMode.HALF_UP));
    }

    public static MonetaryAmount min(MonetaryAmount first, MonetaryAmount second) {
        return first.compareTo(second) < 0 ? first : second;
    }

    public static MonetaryAmount max(MonetaryAmount first, MonetaryAmount second) {
        return first.compareTo(second) >= 0 ? first : second;
    }

    public MonetaryAmount multiply(BigDecimal multiplier) {

        return new MonetaryAmount(amount.multiply(multiplier));
    }

    public MonetaryAmount multiply(Integer multiplier) {
        return new MonetaryAmount(amount.multiply(BigDecimal.valueOf(multiplier)));
    }

    public MonetaryAmount multiply(BigDecimal multiplier, RoundingMode rounding) {
        BigDecimal multiplication = amount.multiply(multiplier);
        return new MonetaryAmount(multiplication.setScale(2, rounding));
    }

    public MonetaryAmount multiply(Percent percent) {
        return percent.multiply(this);
    }

    public MonetaryAmount multiply(Quantity quantity) {
        return quantity.multiply(this);
    }

    public MonetaryAmount divide(Quantity qt) {
        return new MonetaryAmount(amount.divide(qt.getValue(), 2, RoundingMode.HALF_UP));
    }

    public BigDecimal toBigDecimal() {
        return new BigDecimal(amount.toString());// amount;
    }

    @Override
    public int compareTo(MonetaryAmount o) {
        return amount.compareTo(o.getAmount());
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || !(object instanceof MonetaryAmount)) {
            return false;
        }
        return amount.equals(((MonetaryAmount) object).toBigDecimal());
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = hash * 29 + amount.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return this.amount.toString();
    }
}
