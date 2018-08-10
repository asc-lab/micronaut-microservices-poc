package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PolicyDto {
    private String number;
    private LocalDate from;
    private LocalDate to;
    private String policyHolder;
}
