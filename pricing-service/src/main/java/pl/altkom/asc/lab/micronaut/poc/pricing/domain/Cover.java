package pl.altkom.asc.lab.micronaut.poc.pricing.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Cover {

    private String code;
    @Setter
    private BigDecimal price;
}
