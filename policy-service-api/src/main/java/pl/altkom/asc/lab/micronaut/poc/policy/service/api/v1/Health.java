package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Health {
    private String ver;
    private String status;
}
