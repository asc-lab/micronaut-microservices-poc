package pl.altkom.asc.lab.micronaut.poc.product.service.api.v1;

import java.math.BigDecimal;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Introspected
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CoverDto {
    private String code;
    private String name;
    private String description;
    private boolean optional;
    private BigDecimal sumInsured;
}
