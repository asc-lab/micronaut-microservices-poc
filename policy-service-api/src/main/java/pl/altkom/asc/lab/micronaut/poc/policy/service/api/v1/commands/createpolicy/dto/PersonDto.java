package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createpolicy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDto {
    private String firstName;
    private String lastName;
    private String taxId;
}
