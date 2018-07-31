package pl.altkom.asc.lab.micronaut.poc.product.service.api.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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
