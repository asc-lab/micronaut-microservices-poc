package pl.altkom.asc.lab.micronaut.poc.policy.commands.policyregister.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDto {
    private String firstName;
    private String lastName;
    private String pesel;
}
