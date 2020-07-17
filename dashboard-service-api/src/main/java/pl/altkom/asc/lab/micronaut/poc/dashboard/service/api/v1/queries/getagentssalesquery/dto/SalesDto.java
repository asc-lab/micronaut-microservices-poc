package pl.altkom.asc.lab.micronaut.poc.dashboard.service.api.v1.queries.getagentssalesquery.dto;

import java.math.BigDecimal;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Introspected
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SalesDto {
    private Long policiesCount;
    private BigDecimal premiumAmount;
}
