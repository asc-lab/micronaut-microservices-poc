package pl.altkom.asc.lab.micronaut.poc.policy.search.service.api.v1.queries.findpolicy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PolicyListItemDto {
    private String number;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String policyHolder;
}
