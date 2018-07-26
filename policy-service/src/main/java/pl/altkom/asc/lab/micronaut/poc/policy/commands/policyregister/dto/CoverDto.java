package pl.altkom.asc.lab.micronaut.poc.policy.commands.policyregister.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoverDto {
    private String code;
    private List<ServiceDto> services;
}
