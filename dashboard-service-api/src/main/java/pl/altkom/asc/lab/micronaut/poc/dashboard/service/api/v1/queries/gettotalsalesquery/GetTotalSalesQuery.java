package pl.altkom.asc.lab.micronaut.poc.dashboard.service.api.v1.queries.gettotalsalesquery;

import pl.altkom.asc.lab.micronaut.poc.command.bus.api.Query;

import java.time.LocalDate;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Introspected
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetTotalSalesQuery implements Query<GetTotalSalesQueryResult> {
    private String productCode;
    private LocalDate saleDateFrom;
    private LocalDate saleDateTo;
}
