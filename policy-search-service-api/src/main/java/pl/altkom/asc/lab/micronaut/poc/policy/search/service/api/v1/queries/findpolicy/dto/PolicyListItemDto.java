package pl.altkom.asc.lab.micronaut.poc.policy.search.service.api.v1.queries.findpolicy.dto;

import java.time.LocalDate;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Introspected
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PolicyListItemDto {
    private String number;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String policyHolder;
}
