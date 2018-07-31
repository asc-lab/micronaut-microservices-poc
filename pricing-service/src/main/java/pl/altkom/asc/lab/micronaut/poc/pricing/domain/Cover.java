package pl.altkom.asc.lab.micronaut.poc.pricing.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Cover {
    private String code;
    private BigDecimal price;

    public void setPrice(BigDecimal price){
        this.price = price;
    }
}
