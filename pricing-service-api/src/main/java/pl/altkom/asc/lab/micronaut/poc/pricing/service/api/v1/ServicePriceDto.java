package pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServicePriceDto {
    private BigDecimal price;
}
